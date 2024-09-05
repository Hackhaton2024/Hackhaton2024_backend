package fr.hackhaton.hackhaton2024_API.service;

import org.springframework.stereotype.Service;

import fr.hackhaton.hackhaton2024_API.entity.Member;
import fr.hackhaton.hackhaton2024_API.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // Injection du repository par le constructeur
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * Create a member
     * 
     * @param memberToCreate to create
     * @return created member
     * 
     * @author mochizuki
     * @since 2024-09-05
     */
    public Member create(Member member){
        return this.memberRepository.save(member);
    }
}
