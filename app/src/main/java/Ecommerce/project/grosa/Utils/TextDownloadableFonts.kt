package Ecommerce.project.grosa.Utils

import androidx.compose.ui.text.googlefonts.GoogleFont
import Ecommerce.project.grosa.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
// ...


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Inter")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)
