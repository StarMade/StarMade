package org.jaxen.saxpath.base;

final class Verifier
{
  static boolean isXMLNCNameCharacter(char local_c)
  {
    return (isXMLLetter(local_c)) || (isXMLDigit(local_c)) || (local_c == '.') || (local_c == '-') || (local_c == '_') || (isXMLCombiningChar(local_c)) || (isXMLExtender(local_c));
  }
  
  static boolean isXMLNCNameStartCharacter(char local_c)
  {
    return (isXMLLetter(local_c)) || (local_c == '_');
  }
  
  static boolean isXMLLetter(char local_c)
  {
    if (local_c < 'A') {
      return false;
    }
    if (local_c <= 'Z') {
      return true;
    }
    if (local_c < 'a') {
      return false;
    }
    if (local_c <= 'z') {
      return true;
    }
    if (local_c < 'À') {
      return false;
    }
    if (local_c <= 'Ö') {
      return true;
    }
    if (local_c < 'Ø') {
      return false;
    }
    if (local_c <= 'ö') {
      return true;
    }
    if (local_c < 'ø') {
      return false;
    }
    if (local_c <= 'ÿ') {
      return true;
    }
    if (local_c < 'Ā') {
      return false;
    }
    if (local_c <= 'ı') {
      return true;
    }
    if (local_c < 'Ĵ') {
      return false;
    }
    if (local_c <= 'ľ') {
      return true;
    }
    if (local_c < 'Ł') {
      return false;
    }
    if (local_c <= 'ň') {
      return true;
    }
    if (local_c < 'Ŋ') {
      return false;
    }
    if (local_c <= 'ž') {
      return true;
    }
    if (local_c < 'ƀ') {
      return false;
    }
    if (local_c <= 'ǃ') {
      return true;
    }
    if (local_c < 'Ǎ') {
      return false;
    }
    if (local_c <= 'ǰ') {
      return true;
    }
    if (local_c < 'Ǵ') {
      return false;
    }
    if (local_c <= 'ǵ') {
      return true;
    }
    if (local_c < 'Ǻ') {
      return false;
    }
    if (local_c <= 'ȗ') {
      return true;
    }
    if (local_c < 'ɐ') {
      return false;
    }
    if (local_c <= 'ʨ') {
      return true;
    }
    if (local_c < 'ʻ') {
      return false;
    }
    if (local_c <= 'ˁ') {
      return true;
    }
    if (local_c == 'Ά') {
      return true;
    }
    if (local_c < 'Έ') {
      return false;
    }
    if (local_c <= 'Ί') {
      return true;
    }
    if (local_c == 'Ό') {
      return true;
    }
    if (local_c < 'Ύ') {
      return false;
    }
    if (local_c <= 'Ρ') {
      return true;
    }
    if (local_c < 'Σ') {
      return false;
    }
    if (local_c <= 'ώ') {
      return true;
    }
    if (local_c < 'ϐ') {
      return false;
    }
    if (local_c <= 'ϖ') {
      return true;
    }
    if (local_c == 'Ϛ') {
      return true;
    }
    if (local_c == 'Ϝ') {
      return true;
    }
    if (local_c == 'Ϟ') {
      return true;
    }
    if (local_c == 'Ϡ') {
      return true;
    }
    if (local_c < 'Ϣ') {
      return false;
    }
    if (local_c <= 'ϳ') {
      return true;
    }
    if (local_c < 'Ё') {
      return false;
    }
    if (local_c <= 'Ќ') {
      return true;
    }
    if (local_c < 'Ў') {
      return false;
    }
    if (local_c <= 'я') {
      return true;
    }
    if (local_c < 'ё') {
      return false;
    }
    if (local_c <= 'ќ') {
      return true;
    }
    if (local_c < 'ў') {
      return false;
    }
    if (local_c <= 'ҁ') {
      return true;
    }
    if (local_c < 'Ґ') {
      return false;
    }
    if (local_c <= 'ӄ') {
      return true;
    }
    if (local_c < 'Ӈ') {
      return false;
    }
    if (local_c <= 'ӈ') {
      return true;
    }
    if (local_c < 'Ӌ') {
      return false;
    }
    if (local_c <= 'ӌ') {
      return true;
    }
    if (local_c < 'Ӑ') {
      return false;
    }
    if (local_c <= 'ӫ') {
      return true;
    }
    if (local_c < 'Ӯ') {
      return false;
    }
    if (local_c <= 'ӵ') {
      return true;
    }
    if (local_c < 'Ӹ') {
      return false;
    }
    if (local_c <= 'ӹ') {
      return true;
    }
    if (local_c < 'Ա') {
      return false;
    }
    if (local_c <= 'Ֆ') {
      return true;
    }
    if (local_c == 'ՙ') {
      return true;
    }
    if (local_c < 'ա') {
      return false;
    }
    if (local_c <= 'ֆ') {
      return true;
    }
    if (local_c < 'א') {
      return false;
    }
    if (local_c <= 'ת') {
      return true;
    }
    if (local_c < 'װ') {
      return false;
    }
    if (local_c <= 'ײ') {
      return true;
    }
    if (local_c < 'ء') {
      return false;
    }
    if (local_c <= 'غ') {
      return true;
    }
    if (local_c < 'ف') {
      return false;
    }
    if (local_c <= 'ي') {
      return true;
    }
    if (local_c < 'ٱ') {
      return false;
    }
    if (local_c <= 'ڷ') {
      return true;
    }
    if (local_c < 'ں') {
      return false;
    }
    if (local_c <= 'ھ') {
      return true;
    }
    if (local_c < 'ۀ') {
      return false;
    }
    if (local_c <= 'ێ') {
      return true;
    }
    if (local_c < 'ې') {
      return false;
    }
    if (local_c <= 'ۓ') {
      return true;
    }
    if (local_c == 'ە') {
      return true;
    }
    if (local_c < 'ۥ') {
      return false;
    }
    if (local_c <= 'ۦ') {
      return true;
    }
    if (local_c < 'अ') {
      return false;
    }
    if (local_c <= 'ह') {
      return true;
    }
    if (local_c == 'ऽ') {
      return true;
    }
    if (local_c < 'क़') {
      return false;
    }
    if (local_c <= 'ॡ') {
      return true;
    }
    if (local_c < 'অ') {
      return false;
    }
    if (local_c <= 'ঌ') {
      return true;
    }
    if (local_c < 'এ') {
      return false;
    }
    if (local_c <= 'ঐ') {
      return true;
    }
    if (local_c < 'ও') {
      return false;
    }
    if (local_c <= 'ন') {
      return true;
    }
    if (local_c < 'প') {
      return false;
    }
    if (local_c <= 'র') {
      return true;
    }
    if (local_c == 'ল') {
      return true;
    }
    if (local_c < 'শ') {
      return false;
    }
    if (local_c <= 'হ') {
      return true;
    }
    if (local_c < 'ড়') {
      return false;
    }
    if (local_c <= 'ঢ়') {
      return true;
    }
    if (local_c < 'য়') {
      return false;
    }
    if (local_c <= 'ৡ') {
      return true;
    }
    if (local_c < 'ৰ') {
      return false;
    }
    if (local_c <= 'ৱ') {
      return true;
    }
    if (local_c < 'ਅ') {
      return false;
    }
    if (local_c <= 'ਊ') {
      return true;
    }
    if (local_c < 'ਏ') {
      return false;
    }
    if (local_c <= 'ਐ') {
      return true;
    }
    if (local_c < 'ਓ') {
      return false;
    }
    if (local_c <= 'ਨ') {
      return true;
    }
    if (local_c < 'ਪ') {
      return false;
    }
    if (local_c <= 'ਰ') {
      return true;
    }
    if (local_c < 'ਲ') {
      return false;
    }
    if (local_c <= 'ਲ਼') {
      return true;
    }
    if (local_c < 'ਵ') {
      return false;
    }
    if (local_c <= 'ਸ਼') {
      return true;
    }
    if (local_c < 'ਸ') {
      return false;
    }
    if (local_c <= 'ਹ') {
      return true;
    }
    if (local_c < 'ਖ਼') {
      return false;
    }
    if (local_c <= 'ੜ') {
      return true;
    }
    if (local_c == 'ਫ਼') {
      return true;
    }
    if (local_c < 'ੲ') {
      return false;
    }
    if (local_c <= 'ੴ') {
      return true;
    }
    if (local_c < 'અ') {
      return false;
    }
    if (local_c <= 'ઋ') {
      return true;
    }
    if (local_c == 'ઍ') {
      return true;
    }
    if (local_c < 'એ') {
      return false;
    }
    if (local_c <= 'ઑ') {
      return true;
    }
    if (local_c < 'ઓ') {
      return false;
    }
    if (local_c <= 'ન') {
      return true;
    }
    if (local_c < 'પ') {
      return false;
    }
    if (local_c <= 'ર') {
      return true;
    }
    if (local_c < 'લ') {
      return false;
    }
    if (local_c <= 'ળ') {
      return true;
    }
    if (local_c < 'વ') {
      return false;
    }
    if (local_c <= 'હ') {
      return true;
    }
    if (local_c == 'ઽ') {
      return true;
    }
    if (local_c == 'ૠ') {
      return true;
    }
    if (local_c < 'ଅ') {
      return false;
    }
    if (local_c <= 'ଌ') {
      return true;
    }
    if (local_c < 'ଏ') {
      return false;
    }
    if (local_c <= 'ଐ') {
      return true;
    }
    if (local_c < 'ଓ') {
      return false;
    }
    if (local_c <= 'ନ') {
      return true;
    }
    if (local_c < 'ପ') {
      return false;
    }
    if (local_c <= 'ର') {
      return true;
    }
    if (local_c < 'ଲ') {
      return false;
    }
    if (local_c <= 'ଳ') {
      return true;
    }
    if (local_c < 'ଶ') {
      return false;
    }
    if (local_c <= 'ହ') {
      return true;
    }
    if (local_c == 'ଽ') {
      return true;
    }
    if (local_c < 'ଡ଼') {
      return false;
    }
    if (local_c <= 'ଢ଼') {
      return true;
    }
    if (local_c < 'ୟ') {
      return false;
    }
    if (local_c <= 'ୡ') {
      return true;
    }
    if (local_c < 'அ') {
      return false;
    }
    if (local_c <= 'ஊ') {
      return true;
    }
    if (local_c < 'எ') {
      return false;
    }
    if (local_c <= 'ஐ') {
      return true;
    }
    if (local_c < 'ஒ') {
      return false;
    }
    if (local_c <= 'க') {
      return true;
    }
    if (local_c < 'ங') {
      return false;
    }
    if (local_c <= 'ச') {
      return true;
    }
    if (local_c == 'ஜ') {
      return true;
    }
    if (local_c < 'ஞ') {
      return false;
    }
    if (local_c <= 'ட') {
      return true;
    }
    if (local_c < 'ண') {
      return false;
    }
    if (local_c <= 'த') {
      return true;
    }
    if (local_c < 'ந') {
      return false;
    }
    if (local_c <= 'ப') {
      return true;
    }
    if (local_c < 'ம') {
      return false;
    }
    if (local_c <= 'வ') {
      return true;
    }
    if (local_c < 'ஷ') {
      return false;
    }
    if (local_c <= 'ஹ') {
      return true;
    }
    if (local_c < 'అ') {
      return false;
    }
    if (local_c <= 'ఌ') {
      return true;
    }
    if (local_c < 'ఎ') {
      return false;
    }
    if (local_c <= 'ఐ') {
      return true;
    }
    if (local_c < 'ఒ') {
      return false;
    }
    if (local_c <= 'న') {
      return true;
    }
    if (local_c < 'ప') {
      return false;
    }
    if (local_c <= 'ళ') {
      return true;
    }
    if (local_c < 'వ') {
      return false;
    }
    if (local_c <= 'హ') {
      return true;
    }
    if (local_c < 'ౠ') {
      return false;
    }
    if (local_c <= 'ౡ') {
      return true;
    }
    if (local_c < 'ಅ') {
      return false;
    }
    if (local_c <= 'ಌ') {
      return true;
    }
    if (local_c < 'ಎ') {
      return false;
    }
    if (local_c <= 'ಐ') {
      return true;
    }
    if (local_c < 'ಒ') {
      return false;
    }
    if (local_c <= 'ನ') {
      return true;
    }
    if (local_c < 'ಪ') {
      return false;
    }
    if (local_c <= 'ಳ') {
      return true;
    }
    if (local_c < 'ವ') {
      return false;
    }
    if (local_c <= 'ಹ') {
      return true;
    }
    if (local_c == 'ೞ') {
      return true;
    }
    if (local_c < 'ೠ') {
      return false;
    }
    if (local_c <= 'ೡ') {
      return true;
    }
    if (local_c < 'അ') {
      return false;
    }
    if (local_c <= 'ഌ') {
      return true;
    }
    if (local_c < 'എ') {
      return false;
    }
    if (local_c <= 'ഐ') {
      return true;
    }
    if (local_c < 'ഒ') {
      return false;
    }
    if (local_c <= 'ന') {
      return true;
    }
    if (local_c < 'പ') {
      return false;
    }
    if (local_c <= 'ഹ') {
      return true;
    }
    if (local_c < 'ൠ') {
      return false;
    }
    if (local_c <= 'ൡ') {
      return true;
    }
    if (local_c < 'ก') {
      return false;
    }
    if (local_c <= 'ฮ') {
      return true;
    }
    if (local_c == 'ะ') {
      return true;
    }
    if (local_c < 'า') {
      return false;
    }
    if (local_c <= 'ำ') {
      return true;
    }
    if (local_c < 'เ') {
      return false;
    }
    if (local_c <= 'ๅ') {
      return true;
    }
    if (local_c < 'ກ') {
      return false;
    }
    if (local_c <= 'ຂ') {
      return true;
    }
    if (local_c == 'ຄ') {
      return true;
    }
    if (local_c < 'ງ') {
      return false;
    }
    if (local_c <= 'ຈ') {
      return true;
    }
    if (local_c == 'ຊ') {
      return true;
    }
    if (local_c == 'ຍ') {
      return true;
    }
    if (local_c < 'ດ') {
      return false;
    }
    if (local_c <= 'ທ') {
      return true;
    }
    if (local_c < 'ນ') {
      return false;
    }
    if (local_c <= 'ຟ') {
      return true;
    }
    if (local_c < 'ມ') {
      return false;
    }
    if (local_c <= 'ຣ') {
      return true;
    }
    if (local_c == 'ລ') {
      return true;
    }
    if (local_c == 'ວ') {
      return true;
    }
    if (local_c < 'ສ') {
      return false;
    }
    if (local_c <= 'ຫ') {
      return true;
    }
    if (local_c < 'ອ') {
      return false;
    }
    if (local_c <= 'ຮ') {
      return true;
    }
    if (local_c == 'ະ') {
      return true;
    }
    if (local_c < 'າ') {
      return false;
    }
    if (local_c <= 'ຳ') {
      return true;
    }
    if (local_c == 'ຽ') {
      return true;
    }
    if (local_c < 'ເ') {
      return false;
    }
    if (local_c <= 'ໄ') {
      return true;
    }
    if (local_c < 'ཀ') {
      return false;
    }
    if (local_c <= 'ཇ') {
      return true;
    }
    if (local_c < 'ཉ') {
      return false;
    }
    if (local_c <= 'ཀྵ') {
      return true;
    }
    if (local_c < 'Ⴀ') {
      return false;
    }
    if (local_c <= 'Ⴥ') {
      return true;
    }
    if (local_c < 'ა') {
      return false;
    }
    if (local_c <= 'ჶ') {
      return true;
    }
    if (local_c == 'ᄀ') {
      return true;
    }
    if (local_c < 'ᄂ') {
      return false;
    }
    if (local_c <= 'ᄃ') {
      return true;
    }
    if (local_c < 'ᄅ') {
      return false;
    }
    if (local_c <= 'ᄇ') {
      return true;
    }
    if (local_c == 'ᄉ') {
      return true;
    }
    if (local_c < 'ᄋ') {
      return false;
    }
    if (local_c <= 'ᄌ') {
      return true;
    }
    if (local_c < 'ᄎ') {
      return false;
    }
    if (local_c <= 'ᄒ') {
      return true;
    }
    if (local_c == 'ᄼ') {
      return true;
    }
    if (local_c == 'ᄾ') {
      return true;
    }
    if (local_c == 'ᅀ') {
      return true;
    }
    if (local_c == 'ᅌ') {
      return true;
    }
    if (local_c == 'ᅎ') {
      return true;
    }
    if (local_c == 'ᅐ') {
      return true;
    }
    if (local_c < 'ᅔ') {
      return false;
    }
    if (local_c <= 'ᅕ') {
      return true;
    }
    if (local_c == 'ᅙ') {
      return true;
    }
    if (local_c < 'ᅟ') {
      return false;
    }
    if (local_c <= 'ᅡ') {
      return true;
    }
    if (local_c == 'ᅣ') {
      return true;
    }
    if (local_c == 'ᅥ') {
      return true;
    }
    if (local_c == 'ᅧ') {
      return true;
    }
    if (local_c == 'ᅩ') {
      return true;
    }
    if (local_c < 'ᅭ') {
      return false;
    }
    if (local_c <= 'ᅮ') {
      return true;
    }
    if (local_c < 'ᅲ') {
      return false;
    }
    if (local_c <= 'ᅳ') {
      return true;
    }
    if (local_c == 'ᅵ') {
      return true;
    }
    if (local_c == 'ᆞ') {
      return true;
    }
    if (local_c == 'ᆨ') {
      return true;
    }
    if (local_c == 'ᆫ') {
      return true;
    }
    if (local_c < 'ᆮ') {
      return false;
    }
    if (local_c <= 'ᆯ') {
      return true;
    }
    if (local_c < 'ᆷ') {
      return false;
    }
    if (local_c <= 'ᆸ') {
      return true;
    }
    if (local_c == 'ᆺ') {
      return true;
    }
    if (local_c < 'ᆼ') {
      return false;
    }
    if (local_c <= 'ᇂ') {
      return true;
    }
    if (local_c == 'ᇫ') {
      return true;
    }
    if (local_c == 'ᇰ') {
      return true;
    }
    if (local_c == 'ᇹ') {
      return true;
    }
    if (local_c < 'Ḁ') {
      return false;
    }
    if (local_c <= 'ẛ') {
      return true;
    }
    if (local_c < 'Ạ') {
      return false;
    }
    if (local_c <= 'ỹ') {
      return true;
    }
    if (local_c < 'ἀ') {
      return false;
    }
    if (local_c <= 'ἕ') {
      return true;
    }
    if (local_c < 'Ἐ') {
      return false;
    }
    if (local_c <= 'Ἕ') {
      return true;
    }
    if (local_c < 'ἠ') {
      return false;
    }
    if (local_c <= 'ὅ') {
      return true;
    }
    if (local_c < 'Ὀ') {
      return false;
    }
    if (local_c <= 'Ὅ') {
      return true;
    }
    if (local_c < 'ὐ') {
      return false;
    }
    if (local_c <= 'ὗ') {
      return true;
    }
    if (local_c == 'Ὑ') {
      return true;
    }
    if (local_c == 'Ὓ') {
      return true;
    }
    if (local_c == 'Ὕ') {
      return true;
    }
    if (local_c < 'Ὗ') {
      return false;
    }
    if (local_c <= 'ώ') {
      return true;
    }
    if (local_c < 'ᾀ') {
      return false;
    }
    if (local_c <= 'ᾴ') {
      return true;
    }
    if (local_c < 'ᾶ') {
      return false;
    }
    if (local_c <= 'ᾼ') {
      return true;
    }
    if (local_c == 'ι') {
      return true;
    }
    if (local_c < 'ῂ') {
      return false;
    }
    if (local_c <= 'ῄ') {
      return true;
    }
    if (local_c < 'ῆ') {
      return false;
    }
    if (local_c <= 'ῌ') {
      return true;
    }
    if (local_c < 'ῐ') {
      return false;
    }
    if (local_c <= 'ΐ') {
      return true;
    }
    if (local_c < 'ῖ') {
      return false;
    }
    if (local_c <= 'Ί') {
      return true;
    }
    if (local_c < 'ῠ') {
      return false;
    }
    if (local_c <= 'Ῥ') {
      return true;
    }
    if (local_c < 'ῲ') {
      return false;
    }
    if (local_c <= 'ῴ') {
      return true;
    }
    if (local_c < 'ῶ') {
      return false;
    }
    if (local_c <= 'ῼ') {
      return true;
    }
    if (local_c == 'Ω') {
      return true;
    }
    if (local_c < 'K') {
      return false;
    }
    if (local_c <= 'Å') {
      return true;
    }
    if (local_c == '℮') {
      return true;
    }
    if (local_c < 'ↀ') {
      return false;
    }
    if (local_c <= 'ↂ') {
      return true;
    }
    if (local_c == '〇') {
      return true;
    }
    if (local_c < '〡') {
      return false;
    }
    if (local_c <= '〩') {
      return true;
    }
    if (local_c < 'ぁ') {
      return false;
    }
    if (local_c <= 'ゔ') {
      return true;
    }
    if (local_c < 'ァ') {
      return false;
    }
    if (local_c <= 'ヺ') {
      return true;
    }
    if (local_c < 'ㄅ') {
      return false;
    }
    if (local_c <= 'ㄬ') {
      return true;
    }
    if (local_c < '一') {
      return false;
    }
    if (local_c <= 40869) {
      return true;
    }
    if (local_c < 44032) {
      return false;
    }
    return local_c <= 55203;
  }
  
  static boolean isXMLCombiningChar(char local_c)
  {
    if (local_c < '̀') {
      return false;
    }
    if (local_c <= 'ͅ') {
      return true;
    }
    if (local_c < '͠') {
      return false;
    }
    if (local_c <= '͡') {
      return true;
    }
    if (local_c < '҃') {
      return false;
    }
    if (local_c <= '҆') {
      return true;
    }
    if (local_c < '֑') {
      return false;
    }
    if (local_c <= '֡') {
      return true;
    }
    if (local_c < '֣') {
      return false;
    }
    if (local_c <= 'ֹ') {
      return true;
    }
    if (local_c < 'ֻ') {
      return false;
    }
    if (local_c <= 'ֽ') {
      return true;
    }
    if (local_c == 'ֿ') {
      return true;
    }
    if (local_c < 'ׁ') {
      return false;
    }
    if (local_c <= 'ׂ') {
      return true;
    }
    if (local_c == 'ׄ') {
      return true;
    }
    if (local_c < 'ً') {
      return false;
    }
    if (local_c <= 'ْ') {
      return true;
    }
    if (local_c == 'ٰ') {
      return true;
    }
    if (local_c < 'ۖ') {
      return false;
    }
    if (local_c <= 'ۜ') {
      return true;
    }
    if (local_c < '۝') {
      return false;
    }
    if (local_c <= '۟') {
      return true;
    }
    if (local_c < '۠') {
      return false;
    }
    if (local_c <= 'ۤ') {
      return true;
    }
    if (local_c < 'ۧ') {
      return false;
    }
    if (local_c <= 'ۨ') {
      return true;
    }
    if (local_c < '۪') {
      return false;
    }
    if (local_c <= 'ۭ') {
      return true;
    }
    if (local_c < 'ँ') {
      return false;
    }
    if (local_c <= 'ः') {
      return true;
    }
    if (local_c == '़') {
      return true;
    }
    if (local_c < 'ा') {
      return false;
    }
    if (local_c <= 'ौ') {
      return true;
    }
    if (local_c == '्') {
      return true;
    }
    if (local_c < '॑') {
      return false;
    }
    if (local_c <= '॔') {
      return true;
    }
    if (local_c < 'ॢ') {
      return false;
    }
    if (local_c <= 'ॣ') {
      return true;
    }
    if (local_c < 'ঁ') {
      return false;
    }
    if (local_c <= 'ঃ') {
      return true;
    }
    if (local_c == '়') {
      return true;
    }
    if (local_c == 'া') {
      return true;
    }
    if (local_c == 'ি') {
      return true;
    }
    if (local_c < 'ী') {
      return false;
    }
    if (local_c <= 'ৄ') {
      return true;
    }
    if (local_c < 'ে') {
      return false;
    }
    if (local_c <= 'ৈ') {
      return true;
    }
    if (local_c < 'ো') {
      return false;
    }
    if (local_c <= '্') {
      return true;
    }
    if (local_c == 'ৗ') {
      return true;
    }
    if (local_c < 'ৢ') {
      return false;
    }
    if (local_c <= 'ৣ') {
      return true;
    }
    if (local_c == 'ਂ') {
      return true;
    }
    if (local_c == '਼') {
      return true;
    }
    if (local_c == 'ਾ') {
      return true;
    }
    if (local_c == 'ਿ') {
      return true;
    }
    if (local_c < 'ੀ') {
      return false;
    }
    if (local_c <= 'ੂ') {
      return true;
    }
    if (local_c < 'ੇ') {
      return false;
    }
    if (local_c <= 'ੈ') {
      return true;
    }
    if (local_c < 'ੋ') {
      return false;
    }
    if (local_c <= '੍') {
      return true;
    }
    if (local_c < 'ੰ') {
      return false;
    }
    if (local_c <= 'ੱ') {
      return true;
    }
    if (local_c < 'ઁ') {
      return false;
    }
    if (local_c <= 'ઃ') {
      return true;
    }
    if (local_c == '઼') {
      return true;
    }
    if (local_c < 'ા') {
      return false;
    }
    if (local_c <= 'ૅ') {
      return true;
    }
    if (local_c < 'ે') {
      return false;
    }
    if (local_c <= 'ૉ') {
      return true;
    }
    if (local_c < 'ો') {
      return false;
    }
    if (local_c <= '્') {
      return true;
    }
    if (local_c < 'ଁ') {
      return false;
    }
    if (local_c <= 'ଃ') {
      return true;
    }
    if (local_c == '଼') {
      return true;
    }
    if (local_c < 'ା') {
      return false;
    }
    if (local_c <= 'ୃ') {
      return true;
    }
    if (local_c < 'େ') {
      return false;
    }
    if (local_c <= 'ୈ') {
      return true;
    }
    if (local_c < 'ୋ') {
      return false;
    }
    if (local_c <= '୍') {
      return true;
    }
    if (local_c < 'ୖ') {
      return false;
    }
    if (local_c <= 'ୗ') {
      return true;
    }
    if (local_c < 'ஂ') {
      return false;
    }
    if (local_c <= 'ஃ') {
      return true;
    }
    if (local_c < 'ா') {
      return false;
    }
    if (local_c <= 'ூ') {
      return true;
    }
    if (local_c < 'ெ') {
      return false;
    }
    if (local_c <= 'ை') {
      return true;
    }
    if (local_c < 'ொ') {
      return false;
    }
    if (local_c <= '்') {
      return true;
    }
    if (local_c == 'ௗ') {
      return true;
    }
    if (local_c < 'ఁ') {
      return false;
    }
    if (local_c <= 'ః') {
      return true;
    }
    if (local_c < 'ా') {
      return false;
    }
    if (local_c <= 'ౄ') {
      return true;
    }
    if (local_c < 'ె') {
      return false;
    }
    if (local_c <= 'ై') {
      return true;
    }
    if (local_c < 'ొ') {
      return false;
    }
    if (local_c <= '్') {
      return true;
    }
    if (local_c < 'ౕ') {
      return false;
    }
    if (local_c <= 'ౖ') {
      return true;
    }
    if (local_c < 'ಂ') {
      return false;
    }
    if (local_c <= 'ಃ') {
      return true;
    }
    if (local_c < 'ಾ') {
      return false;
    }
    if (local_c <= 'ೄ') {
      return true;
    }
    if (local_c < 'ೆ') {
      return false;
    }
    if (local_c <= 'ೈ') {
      return true;
    }
    if (local_c < 'ೊ') {
      return false;
    }
    if (local_c <= '್') {
      return true;
    }
    if (local_c < 'ೕ') {
      return false;
    }
    if (local_c <= 'ೖ') {
      return true;
    }
    if (local_c < 'ം') {
      return false;
    }
    if (local_c <= 'ഃ') {
      return true;
    }
    if (local_c < 'ാ') {
      return false;
    }
    if (local_c <= 'ൃ') {
      return true;
    }
    if (local_c < 'െ') {
      return false;
    }
    if (local_c <= 'ൈ') {
      return true;
    }
    if (local_c < 'ൊ') {
      return false;
    }
    if (local_c <= '്') {
      return true;
    }
    if (local_c == 'ൗ') {
      return true;
    }
    if (local_c == 'ั') {
      return true;
    }
    if (local_c < 'ิ') {
      return false;
    }
    if (local_c <= 'ฺ') {
      return true;
    }
    if (local_c < '็') {
      return false;
    }
    if (local_c <= '๎') {
      return true;
    }
    if (local_c == 'ັ') {
      return true;
    }
    if (local_c < 'ິ') {
      return false;
    }
    if (local_c <= 'ູ') {
      return true;
    }
    if (local_c < 'ົ') {
      return false;
    }
    if (local_c <= 'ຼ') {
      return true;
    }
    if (local_c < '່') {
      return false;
    }
    if (local_c <= 'ໍ') {
      return true;
    }
    if (local_c < '༘') {
      return false;
    }
    if (local_c <= '༙') {
      return true;
    }
    if (local_c == '༵') {
      return true;
    }
    if (local_c == '༷') {
      return true;
    }
    if (local_c == '༹') {
      return true;
    }
    if (local_c == '༾') {
      return true;
    }
    if (local_c == '༿') {
      return true;
    }
    if (local_c < 'ཱ') {
      return false;
    }
    if (local_c <= '྄') {
      return true;
    }
    if (local_c < '྆') {
      return false;
    }
    if (local_c <= 'ྋ') {
      return true;
    }
    if (local_c < 'ྐ') {
      return false;
    }
    if (local_c <= 'ྕ') {
      return true;
    }
    if (local_c == 'ྗ') {
      return true;
    }
    if (local_c < 'ྙ') {
      return false;
    }
    if (local_c <= 'ྭ') {
      return true;
    }
    if (local_c < 'ྱ') {
      return false;
    }
    if (local_c <= 'ྷ') {
      return true;
    }
    if (local_c == 'ྐྵ') {
      return true;
    }
    if (local_c < '⃐') {
      return false;
    }
    if (local_c <= '⃜') {
      return true;
    }
    if (local_c == '⃡') {
      return true;
    }
    if (local_c < '〪') {
      return false;
    }
    if (local_c <= '〯') {
      return true;
    }
    if (local_c == '゙') {
      return true;
    }
    return local_c == '゚';
  }
  
  static boolean isXMLExtender(char local_c)
  {
    if (local_c < '¶') {
      return false;
    }
    if (local_c == '·') {
      return true;
    }
    if (local_c == 'ː') {
      return true;
    }
    if (local_c == 'ˑ') {
      return true;
    }
    if (local_c == '·') {
      return true;
    }
    if (local_c == 'ـ') {
      return true;
    }
    if (local_c == 'ๆ') {
      return true;
    }
    if (local_c == 'ໆ') {
      return true;
    }
    if (local_c == '々') {
      return true;
    }
    if (local_c < '〱') {
      return false;
    }
    if (local_c <= '〵') {
      return true;
    }
    if (local_c < 'ゝ') {
      return false;
    }
    if (local_c <= 'ゞ') {
      return true;
    }
    if (local_c < 'ー') {
      return false;
    }
    return local_c <= 'ヾ';
  }
  
  static boolean isXMLDigit(char local_c)
  {
    if (local_c < '0') {
      return false;
    }
    if (local_c <= '9') {
      return true;
    }
    if (local_c < '٠') {
      return false;
    }
    if (local_c <= '٩') {
      return true;
    }
    if (local_c < '۰') {
      return false;
    }
    if (local_c <= '۹') {
      return true;
    }
    if (local_c < '०') {
      return false;
    }
    if (local_c <= '९') {
      return true;
    }
    if (local_c < '০') {
      return false;
    }
    if (local_c <= '৯') {
      return true;
    }
    if (local_c < '੦') {
      return false;
    }
    if (local_c <= '੯') {
      return true;
    }
    if (local_c < '૦') {
      return false;
    }
    if (local_c <= '૯') {
      return true;
    }
    if (local_c < '୦') {
      return false;
    }
    if (local_c <= '୯') {
      return true;
    }
    if (local_c < '௧') {
      return false;
    }
    if (local_c <= '௯') {
      return true;
    }
    if (local_c < '౦') {
      return false;
    }
    if (local_c <= '౯') {
      return true;
    }
    if (local_c < '೦') {
      return false;
    }
    if (local_c <= '೯') {
      return true;
    }
    if (local_c < '൦') {
      return false;
    }
    if (local_c <= '൯') {
      return true;
    }
    if (local_c < '๐') {
      return false;
    }
    if (local_c <= '๙') {
      return true;
    }
    if (local_c < '໐') {
      return false;
    }
    if (local_c <= '໙') {
      return true;
    }
    if (local_c < '༠') {
      return false;
    }
    return local_c <= '༩';
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.base.Verifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */