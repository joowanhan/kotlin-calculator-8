package calculator

class Calculator(val line: String) {
	
	val endCustomDelimiters = line.indexOf("\n")
	var delimiters = mutableListOf(',', ':')
	var numbersString = ""
	lateinit var numbers: List<Int>
	
	fun parseCustomDelimiters() {
		// 커스텀 구분자(//와 \n 사이) 추출 로직
		if (line.startsWith("//")) {
			val customDelimiters = line.substring(2, endCustomDelimiters).map { it.toChar() }
			delimiters.addAll(customDelimiters)
		}
	}
	
	fun parseNumbers() {
		numbersString = line.substring(endCustomDelimiters + 1)
		numbers = numbersString.split(*delimiters.toCharArray()).filter { it.isNotBlank() } // (권장) 연속된 구분자로 인한 빈 문자열 제거
			.map { it.trim().toInt() }  // 공백 제거 후 정수로 변환
	}
	
	fun calculate(): Int {
		var sum = 0
		parseCustomDelimiters()
		parseNumbers()
		require(numbers.all { it >= 0 })
		numbers.map { sum += it }
		return sum
		
	}
	
}