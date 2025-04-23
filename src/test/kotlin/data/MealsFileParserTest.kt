package data

import com.google.common.truth.Truth.*
import com.thechance.data.MealsDataException
import com.thechance.data.MealsFileParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.text.SimpleDateFormat

class MealsFileParserTest {
 private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
 private val parser = MealsFileParser(dateFormat)

 @Test
 fun `should return valid meal when line is a valid`() {
  //given
  val line = "Grilled Salmon,13,55,47,2005-09-16,\"['seafood']\",\"[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['if desired , season with salt']\",\"autumn is my favorite time of year to cook this recipe\",\"['salt']\",7"

  //when
  val meal = parser.parseRecord(line)

  //then
  assertThat(meal?.name).isEqualTo("Grilled Salmon")
 }

 @Test
 fun `should throw InvalidFieldsCountException when fields count is not exactly 12`() {
  //given
  val line = "name, 112, 40, 123, '2025-10-30', []"
  //when & then
  assertThrows<MealsDataException.InvalidFieldsCountException> {
   parser.parseRecord(line)
  }
 }

 @Test
 fun `should throw InvalidNumericFormatException when nutritionFacts contains non-numeric value`() {
  //given
  val line = "Grilled Salmon,13,55,47,2005-09-16,\"['seafood']\",\"[51a5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['if desired , season with salt']\",\"autumn is my favorite time of year to cook this recipe\",\"['salt']\",7"

  //when & then
  assertThrows<MealsDataException.InvalidNumericFormatException> {
   parser.parseRecord(line)
  }
 }

 @Test
 fun `should throw InvalidListFieldException when tags is not in a list format`() {
  //given
  val line = "Grilled Salmon,13,55,47,2005-09-16,\"\",\"[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['if desired , season with salt']\",\"autumn is my favorite time of year to cook this recipe\",\"['salt']\",7"

  //when & then
  assertThrows<MealsDataException.InvalidListFieldException> {
   parser.parseRecord(line)
  }
 }

 @Test
 fun `should throw InvalidNumericFormatException when non-numeric ID provided`() {
  // given
  val line = "Grilled Salmon,NN,55,47,2005-09-16,\"['seafood']\",\"[51a5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['if desired , season with salt']\",\"autumn is my favorite time of year to cook this recipe\",\"['salt']\",7"

  //when & then
  assertThrows<MealsDataException.InvalidNumericFormatException> {
   parser.parseRecord(line)
  }
 }
}