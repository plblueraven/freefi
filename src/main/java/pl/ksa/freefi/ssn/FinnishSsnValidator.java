package pl.ksa.freefi.ssn;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class FinnishSsnValidator implements LocalisedSsnValidator {

    private static final Map<Integer, String> CONTROL_SIGNS = Map.ofEntries(
            Map.entry(0, "0"),
            Map.entry(1, "1"),
            Map.entry(2, "2"),
            Map.entry(3, "3"),
            Map.entry(4, "4"),
            Map.entry(5, "5"),
            Map.entry(6, "6"),
            Map.entry(7, "7"),
            Map.entry(8, "8"),
            Map.entry(9, "9"),
            Map.entry(10, "A"),
            Map.entry(11, "B"),
            Map.entry(12, "C"),
            Map.entry(13, "D"),
            Map.entry(14, "E"),
            Map.entry(15, "F"),
            Map.entry(16, "H"),
            Map.entry(17, "J"),
            Map.entry(18, "K"),
            Map.entry(19, "L"),
            Map.entry(20, "M"),
            Map.entry(21, "N"),
            Map.entry(22, "P"),
            Map.entry(23, "R"),
            Map.entry(24, "S"),
            Map.entry(25, "T"),
            Map.entry(26, "U"),
            Map.entry(27, "V"),
            Map.entry(28, "W"),
            Map.entry(29, "X"),
            Map.entry(30, "Y")
    );
    private static final String ALLOWED_CONTROL_CHARACTERS_REGEXP_PART = "([" + String.join("", CONTROL_SIGNS.values()) + "])";
    private static final String ALLOWED_DIVIDE_SIGN_REGEXP_PART = "[-+A]";
    private static final Pattern SSN_PATTERN = Pattern.compile("(\\d{6})" + ALLOWED_DIVIDE_SIGN_REGEXP_PART + "(\\d{3})" + ALLOWED_CONTROL_CHARACTERS_REGEXP_PART);
    private static final int CONTROL_GENERATOR = 31;

    @Override
    public String getSupportedCountryCode() {
        return "FI";
    }

    @Override
    public boolean isValid(String ssn) {
        final Matcher matcher = SSN_PATTERN.matcher(ssn);
        if (!matcher.matches()) {
            return false;
        }
        if (matcher.groupCount() != 3) {
            return false;
        }
        final String dateOfBirthGroup = matcher.group(1);
        final String individualNumberGroup = matcher.group(2);
        final int dataToControl;
        try {
            dataToControl = Integer.parseInt(dateOfBirthGroup + individualNumberGroup);
        } catch (NumberFormatException e) {
            return false;
        }
        final Integer controlSign = (int) Math.round(Math.floorMod(dataToControl, CONTROL_GENERATOR) / (double) CONTROL_GENERATOR * CONTROL_GENERATOR);
        return CONTROL_SIGNS.get(controlSign).equals(matcher.group(3));
    }
}
