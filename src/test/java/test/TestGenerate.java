package test;

import com.codeborne.selenide.Condition;
import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class TestGenerate {

   @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("input[placeholder = 'Город']").sendKeys(validUser.getCity());
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        $("input[placeholder = 'Дата встречи']").sendKeys(deleteString);
        $("input[placeholder = 'Дата встречи']").sendKeys(firstMeetingDate);
        $("input[name = 'name']").sendKeys(validUser.getName());
        $("input[name = 'phone']").sendKeys(validUser.getPhone());
        $("span[class = 'checkbox__box']").click();
        $(By.className("button")).click();
        $(By.className("notification__content")).
                shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate)).
                shouldBe(Condition.visible, Duration.ofSeconds(10));
        $(By.className("notification__closer")).click();
        $("input[placeholder = 'Дата встречи']").sendKeys(deleteString);
        $("input[placeholder = 'Дата встречи']").sendKeys(secondMeetingDate);
        //$("span[class = 'checkbox__box']").click();
        $(By.className("button")).click();
        $$(By.className("notification__content")).get(1).
                shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).
                shouldBe(Condition.visible, Duration.ofSeconds(10));
        $("button[role = 'button']").click();

    }
}
