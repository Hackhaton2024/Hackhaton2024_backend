package fr.hackhaton.hackhaton2024_API.service;

import fr.hackhaton.hackhaton2024_API.dto.MemberRequestDto;
import fr.hackhaton.hackhaton2024_API.dto.MemberResponseDto;
import fr.hackhaton.hackhaton2024_API.entity.Member;
import fr.hackhaton.hackhaton2024_API.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional //garantir que toutes les opérations se déroulent dans une transaction cohérente
public class MemberService {

    private final MemberRepository memberRepository;

    // Injection du repository par le constructeur
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member memberToCreate = buildMemberFromRequest(memberRequestDto);

        Member createdMember = this.create(memberToCreate);

        return convertToResponseDto(createdMember);
    }
    private Member buildMemberFromRequest(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .firstName(memberRequestDto.firstName())
                .build();
    }

    private MemberResponseDto convertToResponseDto(Member createdMember) {
        return new MemberResponseDto(createdMember.getId(), createdMember.getFirstName());
    }
    /**Create a member
     *
     * @param member to create
     * @return created member
     * 
     * @author mochizuki
     * @since 2024-09-05
     */
    public Member create(Member member){
        return this.memberRepository.save(member);
    }
}
