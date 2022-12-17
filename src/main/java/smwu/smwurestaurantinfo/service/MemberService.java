package smwu.smwurestaurantinfo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smwu.smwurestaurantinfo.api.dto.GenerateMemberRequestDto;
import smwu.smwurestaurantinfo.entity.member.Member;
import smwu.smwurestaurantinfo.repository.MemberRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public Long generateNewMember(GenerateMemberRequestDto memberGenerateDto) {
        memberRepository.save(memberGenerateDto.toEntity());
        try {
            return findByEmail(memberGenerateDto.getEmail()).get().getId();
        } catch (NoSuchElementException e) {
            return generateNewMember(memberGenerateDto);
        }
    }
}
