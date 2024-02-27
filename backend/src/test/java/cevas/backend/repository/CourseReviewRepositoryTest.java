package cevas.backend.repository;

import cevas.backend.domain.Course;
import cevas.backend.domain.CourseReview;
import cevas.backend.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseReviewRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseReviewRepository courseReviewRepository;

    @Test
    public void findByMemberIdAndCourseId_Test() throws Exception {
        //given
        Member member = Member.createMember("yonghyunkwon98@gmail.com", "yonghyun", "abcdefg", "2018", "Computer Science");
        Member savedMember = memberRepository.save(member);

        Course course = Course.createCourse("COMP3230", "Operating System", "Engineering");
        Course savedCourse = courseRepository.save(course);


        CourseReview courseReview = CourseReview.createCourseReview(
                savedMember,
                savedCourse,
                "2024",
                "A+",
                5,
                5,
                5,
                5,
                5,
                20,
                20,
                20,
                30
        );
        CourseReview savedCourseReview = courseReviewRepository.save(courseReview);

        //when
        CourseReview findCourseReview = courseReviewRepository.findByMemberIdAndCourseId(savedMember.getId(), savedCourse.getId());

        //then
        assertThat(findCourseReview.getId()).isEqualTo(savedCourseReview.getId());
        assertThat(findCourseReview.getMember().getId()).isEqualTo(savedMember.getId());
        assertThat(findCourseReview.getCourse().getId()).isEqualTo(savedCourse.getId());
    }
}