package data

import com.thechance.data.MealsFileReader
import fake.createFileFromMealText
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.*
import fake.multiLinedMealText
import fake.validMealText


class MealsFileReaderTest {

  @Test
  fun `should return one valid string meal for every valid record`() {
   //given
   val reader = MealsFileReader(createFileFromMealText(validMealText))
   //when
   val records = reader.readMealRecords()

   //then
   assertThat(records).hasSize(1)
  }

  @Test
  fun `should read exactly one meal when meal is multi-lined`() {
   //given
   val reader = MealsFileReader(createFileFromMealText(multiLinedMealText))
   //when
   val records = reader.readMealRecords()

   //then
   assertThat(records).hasSize(1)
  }
 }