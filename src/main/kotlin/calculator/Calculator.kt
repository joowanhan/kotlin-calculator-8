package calculator

class Calculator(val line: String) {
	
	var delimiters = mutableListOf(',', ':')
	var numbersString = line // 커스텀 구분자가 없는 평범한 입력(예: 1,2:3)"을 처리해야 하기 위해 초기화
	
	fun parseCustomDelimiters() {
		// 커스텀 구분자(//와 \n 사이) 추출 로직
		if (line.startsWith("//")) {
			// 정규표현식을 이용해 커스텀구분자(들)을 분리
			// (?: ... )는 비캡처 그룹, groupValues 인덱스 순서 영향 X
			// (?:\\n|\n)을 사용하여 '문자열 \n'과 '실제 줄바꿈' 모두 매칭
			val regex = Regex("""^//(.*)(?:\n|\\n)(.*)$""")
			val matchResult = regex.find(line)
			if (matchResult != null) {
				// 숫자 문자열 저장
				numbersString = matchResult.groupValues[2]
				// 커스텀 구분자 저장 (문자열을 문자 리스트로 즉시 변환)
				val customDelimiters = matchResult.groupValues[1].toList()
				delimiters.addAll(customDelimiters)
				
			}
			
		}
	}
	
	fun parseNumbers(): List<Int> {
		return numbersString.split(*delimiters.toCharArray()) // 스프레드를 위해 Array로 변환, vararg 인자에 투입
			.filter { it.isNotBlank() } // 연속된 구분자로 생길 수 있는 빈 문자열 걸러내기 (권장)
			.map {
				it.trim().toIntOrNull() ?: throw IllegalArgumentException("Invalid number format: $it")
			}  // trim: 문자열 맨 앞뒤의 공백 제거
	}
	
	fun calculate(): Int {
		parseCustomDelimiters()
		val numbers = parseNumbers()
		require(numbers.all { it >= 0 }) { "Negative integers can't be calculated" }
		return numbers.sum()
		
	}
	
}
