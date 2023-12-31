package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

	public static void main(String[] args) {
		// DIP 위반 코드
//		MemberService memberService = new MemberServiceImpl(null);
//		OrderService orderService = new OrderServiceImpl(null, null );
//		
//		Long memberId = 1L;
//		
//		Member member = new Member(memberId, "memberA", Grade.VIP);
//		memberService.join(member);
//		
//		Order order = orderService.createOrder(memberId, "itemA", 10000);
//		
//		System.out.println("order = " + order.toString());
//		System.out.println("order.calculratePrice = " + order.calculatePrice());
		
		// DIP 문제 해결 코드
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
//		OrderService orderService = appConfig.orderService();
//		
//		Long memberId = 1L;
//		
//		Member member = new Member(memberId, "memberA", Grade.VIP);
//		memberService.join(member);
//		
//		Order order = orderService.createOrder(memberId, "itemA", 20000);
//		
//		System.out.println("order = " + order.toString());
//		System.out.println("order.calculratePrice = " + order.calculatePrice());
		
		// 스프링 전환
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		// ApplicationContext를 스프링 컨테이너라고 한다.
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		OrderService orderService = ac.getBean("orderService", OrderService.class);
		
		Long memberId = 1L;
		
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 20000);
		
		System.out.println("order = " + order.toString());
		System.out.println("order.calculratePrice = " + order.calculatePrice());
	}

}
