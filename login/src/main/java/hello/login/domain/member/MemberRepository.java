package hello.login.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Member save(Member member) {
		member.setId(++sequence);
		log.info("save : member = {}", member);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public Optional<Member> findByLoginId(String loginId){
//		List<Member> all = findAll();
//		for(Member m : all) {
//			if(m.getLoginId().equals(loginId)) {
//				return Optional.of(m);
//			}
//		}
//		return Optional.empty();
		
		return findAll().stream()
			   .filter(m -> m.getLoginId().equals(loginId))
			   .findAny();
	}
	
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}
	
	//Test 용도
	public void clearStore() {
		store.clear();
	}
}
