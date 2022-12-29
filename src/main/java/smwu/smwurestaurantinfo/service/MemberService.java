package smwu.smwurestaurantinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smwu.smwurestaurantinfo.api.dto.GenerateMemberRequestDto;
import smwu.smwurestaurantinfo.domain.member.Member;
import smwu.smwurestaurantinfo.repository.MemberRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findOneByEmail(email);
    }
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 회원을 찾을 수 없습니다.")
        );
    }

    @Transactional
    public Long generateNewMember(GenerateMemberRequestDto memberGenerateDto) {
        memberRepository.save(memberGenerateDto.toEntity());
        try {
            return findByEmail(memberGenerateDto.getEmail()).get().getId();
        } catch (NoSuchElementException e) {
            return generateNewMember(memberGenerateDto);
        }
    }

    // 숙대 학생임을 인증한 회원에 대해 UserStatus를 AUTHENTICATED로 변경
    @Transactional
    public void giveAuthToAuthenticatedMember(Long memberId) {
        findById(memberId).updateStatus();
    }

    @Transactional
    public void giveAuthToAdmin(Long adminMemberId) {
        findById(adminMemberId).appointMemberToAdmin();
    }
    // 로그아웃 : https://developers.naver.com/forum/posts/25174
}
