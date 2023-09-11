//package petmily.mapper;
//
//import petmily.domain.adopt_review.AdoptReview;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import petmily.mapper.AdoptReviewMapper;
//
//import java.util.List;
//
//@SpringBootTest
//public class AdoptReviewMapperTest {
//
//    @Autowired
//    AdoptReviewMapper mapper;
//
//    @Test
//    void count_with_condition() {
//        int result1 = mapper.selectCountWithCondition("입양후기", "allSearch", "ggg");
//        int result2 = mapper.selectCountWithCondition("입양후기", "allSearch", "allKeyword");
//        int result3 = mapper.selectCountWithCondition("입양후기", "name", "테스트");
//
//        System.out.println("result1 = " + result1);
//        System.out.println("result2 = " + result2);
//        System.out.println("result3 = " + result3);
//    }
//
//    @Test
//    void select_with_condition() {
//        List<AdoptReview> adoptReviewList1 = mapper.selectIndexWithCondition(1, 3, "입양후기", "name", "테스트");
//        List<AdoptReview> adoptReviewList2 = mapper.selectIndexWithCondition(1, 80, "입양후기", "allSearch", "allKeyword");
//
//        for (AdoptReview adoptReview1 : adoptReviewList1) {
//            System.out.println("adoptReview1 = " + adoptReview1);
//        }
//
//        for (AdoptReview adoptReview2 : adoptReviewList2) {
//            System.out.println("adoptReview2 = " + adoptReview2);
//        }
//    }
//}
