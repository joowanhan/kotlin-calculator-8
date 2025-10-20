package calculator

// 과제 요구사항에 있는 Console 라이브러리를 사용하기 위해 import
import camp.nextstep.edu.missionutils.Console

fun main() {
    // 1. "덧셈할 문자열을 입력해 주세요." 문구를 화면에 출력
    // println()은 괄호 안의 내용을 출력하고 줄을 바꿔주는 함수
    println("덧셈할 문자열을 입력해 주세요.")

    // 2. 사용자로부터 문자열을 입력받아 input 변수에 저장
    // val은 변수를 선언하는 키워드. 한 번 값이 할당되면 바꿀 수 없음 (Value)
    // Console.readLine()은 사용자가 터미널에 입력한 한 줄을 읽어오는 기능을 함
    val input = Console.readLine()

    // 3. StringCalculator 객체의 add 함수를 호출하여 결과를 받음
    val result = StringCalculator.add(input)

    // 4. 최종 결과를 형식에 맞게 출력
    // 문자열 안에 변수를 넣을 때는 '$' 기호를 사용. '문자열 템플릿'
    println("결과 : $result")
}

// 계산기 로직을 담당하는 싱글톤 객체
// object 키워드는 클래스 전체에서 단 하나의 인스턴스만 생성됨을 보장함.
object StringCalculator {
    // 문자열을 입력받아 합계를 반환하는 함수
    // text: String? 에서 '?'는 이 변수가 null 값을 가질 수도 있다는 것을 의미. (코틀린의 Null 안정성)
    fun add(text: String?): Int {
        // 입력값이 null이거나 공백이면 0을 반환
        if (text.isNullOrBlank()) {
            return 0
        }

        // split() 함수로 문자열을 쉼표(,) 또는 콜론(:) 기준으로 분리하여 리스트로 만듦
        // 정규식을 사용하여 여러 구분자를 한 번에 처리
        val numbers = text.split("[,:]".toRegex())

        // sumOf()를 사용하여 각 문자열을 정수(Int)로 변환하고 그 합계를 계산
        // 'it'은 리스트의 각 항목(여기서는 "1", "2", "3" 등)을 의미
        return numbers.sumOf { it.toInt() }
    }
}
