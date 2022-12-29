package smwu.smwurestaurantinfo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import smwu.smwurestaurantinfo.domain.member.Member;
import smwu.smwurestaurantinfo.repository.MemberRepository;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitDataConfig implements CommandLineRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Member> initMembers = getInitSamplesFromFile();
        if (initMembers == null || initMembers.size() == 0) {
            throw new IllegalArgumentException("no data in init data file.");
        }
        memberRepository.saveAll(initMembers);
    }

    private List<Member> getInitSamplesFromFile() throws IOException {
        List<Member> members;
        String FILE_INIT_SAMPLE = "InitData.json";
        try (InputStream inputStream = getStreamFromResource(FILE_INIT_SAMPLE)) {
            JsonNode sampleNode = getSampleNode(inputStream);
            members = getSampleListFromNode(sampleNode);
        }
        return members;
    }

    private InputStream getStreamFromResource(String fileLocation) {
        ClassLoader classLoader = InitDataConfig.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileLocation);

        if (inputStream == null) {
            throw new IllegalArgumentException("init data file\"" + fileLocation + "\" not found.");
        } else {
            return inputStream;
        }
    }

    private JsonNode getSampleNode(InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode sampleNode = null;
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            sampleNode = objectMapper.readTree(dis).path("initMembers");
        } catch (IOException io) {
            io.printStackTrace();
        }
        return sampleNode;
    }

    private List<Member> getSampleListFromNode(JsonNode sampleNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        return new ArrayList<>(
                objectMapper.convertValue(sampleNode, new TypeReference<List<Member>>() {})
        );
    }
}
