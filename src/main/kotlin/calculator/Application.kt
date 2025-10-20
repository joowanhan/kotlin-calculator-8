package calculator

// 과제 요구사항에 있는 Console 라이브러리를 사용하기 위해 import
import camp.nextstep.edu.missionutils.Console

fun main() {
    // 1. "덧셈할 문자열을 입력해 주세요." 문구를 화면에 출력
    // println()은 괄호 안의 내용을 출력하고 줄을 바꿔주는 함수
    println("덧셈할 문자열을 입력해 주세요.")

    // 2. 사용자로부터 문자열을 입력받아 input 변수에 저장
    // val은 변수를 선언하는 키워드 한 번 값이 할당되면 바꿀 수 없음 (Value)
    // Console.readLine()은 사용자가 터미널에 입력한 한 줄을 읽어오는 기능을 함
    val input = Console.readLine()

    // 3. 우선 입출력 기능만 구현하는 것이므로, 계산 결과는 임시로 0으로 설정
    // 앞으로 이 부분을 실제 계산 로직으로 채워나갈 것
    val result = 0

    // 4. 최종 결과를 형식에 맞게 출력
    // 문자열 안에 변수를 넣을 때는 '$' 기호를 사용. '문자열 템플릿'
    println("결과 : $result")
}
