package core;

import core.config.AppConfig;
import core.model.Performance;
import core.model.PerformanceSession;
import core.model.Stage;
import core.model.User;
import core.security.AuthenticationService;
import core.service.OrderService;
import core.service.PerformanceService;
import core.service.PerformanceSessionsService;
import core.service.ShoppingCartService;
import core.service.StageService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);
        final OrderService orderService = context.getBean(OrderService.class);
        final PerformanceService performanceService = context.getBean(PerformanceService.class);
        final PerformanceSessionsService performanceSessionsService = context
                .getBean(PerformanceSessionsService.class);
        final ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        final StageService stageService = context.getBean(StageService.class);
        final AuthenticationService authenticationService =
                context.getBean(AuthenticationService.class);
        
        Performance orfeo = new Performance();
        orfeo.setTitle("L'Orfeo");
        orfeo.setDescription(
                "With a mythological musician as hero, L'Orfeo ranks as the first great opera.");
        performanceService.add(orfeo);
        System.out.println(performanceService.get(orfeo.getId()));
        performanceService.getAll().forEach(System.out::println);
        
        Stage firstStage = new Stage();
        firstStage.setCapacity(100);
        firstStage.setDescription("first stage with capacity 100");
        
        Stage secondStage = new Stage();
        secondStage.setCapacity(200);
        secondStage.setDescription("second stage with capacity 200");
        
        stageService.add(firstStage);
        stageService.add(secondStage);
        
        System.out.println(stageService.getAll());
        System.out.println(stageService.get(firstStage.getId()));
        
        PerformanceSession tomorrowPerformanceSession = new PerformanceSession();
        tomorrowPerformanceSession.setStage(firstStage);
        tomorrowPerformanceSession.setPerformance(orfeo);
        tomorrowPerformanceSession.setShowTime(LocalDateTime.now().plusDays(1L));
        
        PerformanceSession yesterdayPerformanceSession = new PerformanceSession();
        yesterdayPerformanceSession.setStage(secondStage);
        yesterdayPerformanceSession.setPerformance(orfeo);
        yesterdayPerformanceSession.setShowTime(LocalDateTime.now().minusDays(1L));
        
        performanceSessionsService.add(tomorrowPerformanceSession);
        performanceSessionsService.add(yesterdayPerformanceSession);
        
        System.out.println(performanceSessionsService.get(yesterdayPerformanceSession.getId()));
        System.out.println(
                performanceSessionsService.findAvailableSessions(orfeo.getId(), LocalDate.now()));
        
        User user = authenticationService.register("vovazalli@gmail.com", "qwerty");
        shoppingCartService.addSession(tomorrowPerformanceSession, user);
        shoppingCartService.addSession(yesterdayPerformanceSession, user);
        
        System.out.println("Shopping cart with tickets:\n" + shoppingCartService.getByUser(user));
        System.out.println(orderService.completeOrder(shoppingCartService.getByUser(user)));
        System.out.println("Order History:\n " + orderService.getOrdersHistory(user));
        System.out.println(
                "Shopping cart after completing order:\n" + shoppingCartService.getByUser(user));
    }
}
