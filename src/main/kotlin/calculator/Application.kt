package calculator

// 과제 요구사항에 있는 Console 라이브러리를 사용하기 위해 import
import camp.nextstep.edu.missionutils.Console

fun main() {
    // 사용자 입력을 요청하는 문구 출력
    println("덧셈할 문자열을 입력해 주세요.")

    // 사용자로부터 문자열 입력받기
    // val은 변수를 선언하는 키워드. 한 번 값이 할당되면 바꿀 수 없음 (Value)
    val input = Console.readLine()

    // 계산 로직 호출 및 결과 저장
    val result = StringCalculator.add(input)

    // 최종 결과 출력
    // 문자열 안에 변수를 넣을 때는 '$' 기호를 사용. '문자열 템플릿'
    println("결과 : $result")
}

// 문자열 계산기 로직을 담당하는 싱글톤 객체
// object 키워드는 클래스 전체에서 단 하나의 인스턴스만 생성됨을 보장함
object StringCalculator {
    // 커스텀 구분자 정의 부분을 찾기 위한 정규 표현식
    // StringCalculator 객체 안에서만 사용되므로 private
    private val customDelimiterPattern = Regex("//(.*)\n(.*)")

    // 문자열을 입력받아 합계를 반환하는 함수
    // text: String? 에서 '?'는 이 변수가 null 값을 가질 수도 있다는 것을 의미. (코틀린의 Null 안정성)
    fun add(text: String?): Int {
        if (text.isNullOrBlank()) {
            return 0
        }
        // 사용자가 입력한 \\n을 실제 개행 문자로 치환
        val processedText = text.replace("\\n", "\n")

        // 1. 입력을 분석하여 숫자 문자열과 모든 구분자 목록을 가져옴
        val (numberString, delimiters) = parseInput(processedText)

        // 2. 모든 구분자를 사용하여 문자열을 분리하고 합계를 계산
        // joinToString으로 구분자들을 |(OR)로 연결하여 하나의 정규식 패턴을 생성 (예: ",|:|;")
        val splitRegex = delimiters.joinToString(separator = "|") { Regex.escape(it) }.toRegex()

        return numberString.split(splitRegex)
            .sumOf { it.toInt() }
    }


    // 입력 문자열을 분석하여 (숫자 문자열, 구분자 목록) 쌍을 반환하는 함수
    private fun parseInput(text: String): Pair<String, List<String>> {
        val matchResult = customDelimiterPattern.find(text)

        // 기본 구분자 목록을 변경 가능한 리스트로 생성
        val delimiters = mutableListOf(",", ":")

        // 커스텀 구분자 패턴이 발견된 경우
        if (matchResult != null) {
            val (customDelimiter, numberPart) = matchResult.destructured
            // 커스텀 구분자 문자열의 각 문자를 개별 구분자로 추가
            delimiters.addAll(customDelimiter.map { it.toString() })
            // (숫자 부분, 최종 구분자 목록) 반환
            return numberPart to delimiters
        }

        // 커스텀 구분자가 없는 경우
        // (원본 문자열 전체, 기본 구분자 목록) 반환
        return text to delimiters
    }
}
