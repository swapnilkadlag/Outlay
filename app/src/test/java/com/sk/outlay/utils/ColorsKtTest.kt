package com.sk.outlay.utils

import com.google.common.truth.Truth.assertThat
import com.sk.outlay.data.enums.OutlayColor
import org.junit.Test

class ColorsKtTest {
    @Test
    fun getColorForString() {
        assertThat(getColorForString("Abc")).isEqualTo(OutlayColor.Red)
        assertThat(getColorForString("z")).isEqualTo(OutlayColor.DeepOrange)
        assertThat(getColorForString("n")).isEqualTo(OutlayColor.Red)
    }
}