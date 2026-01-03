package calculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

class CalculatorTest {
	
	@Test
	@DisplayName("쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열의 합을 반환한다")
	fun calculate_basic_delimiters() {
		// given
		val input = "1,2:3"
		val calculator = Calculator(input)
		
		// when
		val result = calculator.calculate()
		
		// then
		assertThat(result).isEqualTo(6)
	}
	
	@Test
	@DisplayName("커스텀 구분자를 사용하는 경우 해당 구분자로 숫자를 분리하여 합을 반환한다")
	fun calculate_custom_delimiter() {
		// given
		// 실제 입력 환경에서는 줄바꿈이 \n으로 들어오므로 이를 시뮬레이션
		val input = "//;\n1;2;3"
		val calculator = Calculator(input)
		
		// when
		val result = calculator.calculate()
		
		// then
		assertThat(result).isEqualTo(6)
	}
	
	@Test
	@DisplayName("문자열 내부에 음수가 포함된 경우 IllegalArgumentException을 발생시킨다")
	fun calculate_negative_number_exception() {
		// given
		val input = "//;\\n1;-2,3:4"
		val calculator = Calculator(input)
		
		// when & then
		assertThatThrownBy { calculator.calculate() }.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessage("negative integers can't be calculated")
	}
	
	@Test
	@DisplayName("문자열 내부에 숫자 이외의 값(문자 등)이 포함된 경우 IllegalArgumentException을 발생한다")
	fun calculate_invalid_number_format() {
		// given
		val input = "//;\n1;a,3:4"
		val calculator = Calculator(input)
		
		// when & then
		// toInt() 변환 과정에서 숫자가 아닌 경우 예외 발생 (코드의 map { it.trim().toInt() } 부분)
		assertThatThrownBy { calculator.calculate() }.isInstanceOf(IllegalArgumentException::class.java)
		// 현재는 NumberFormatException이 발생됨 - 하지만 조상이 IllegalArgumentException이라서 테스트가 통과됨 - 리팩토링하기
	}
	
	@Test
	@DisplayName("빈 문자열 입력 시 0을 반환한다")
	fun calculate_empty_string() {
		// given
		val input = ""
		// 현재 로직상 빈 문자열 분리 -> 빈 리스트 -> sum()은 0 반환
		val calculator = Calculator(input)
		
		// when
		val result = calculator.calculate()
		
		// then
		assertThat(result).isEqualTo(0)
	}
}