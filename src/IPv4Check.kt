fun main() {
    // Valid IPv4 addresses
    check(
        "Standard valid IPv4 should return true",
        result = isValidIPv4("192.168.1.1"),
        correctResult = true
    )

    check(
        "Minimum valid IPv4 should return true",
        result = isValidIPv4("0.0.0.0"),
        correctResult = true
    )

    check(
        "Maximum valid IPv4 should return true",
        result = isValidIPv4("255.255.255.255"),
        correctResult = true
    )

    check(
        "Mixed valid IPv4 should return true",
        result = isValidIPv4("10.0.128.255"),
        correctResult = true
    )

    // Invalid IPv4 addresses - Format issues
    check(
        "Too few segments should return false",
        result = isValidIPv4("192.168.1"),
        correctResult = false
    )

    check(
        "Too many segments should return false",
        result = isValidIPv4("192.168.1.1.1"),
        correctResult = false
    )

    check(
        "Empty string should return false",
        result = isValidIPv4(""),
        correctResult = false
    )

    check(
        "Missing dots should return false",
        result = isValidIPv4("19216811"),
        correctResult = false
    )

    check(
        "Extra characters should return false",
        result = isValidIPv4("192.168.1.1a"),
        correctResult = false
    )

    // Invalid IPv4 addresses - Number range issues
    check(
        "Segment > 255 should return false",
        result = isValidIPv4("256.168.1.1"),
        correctResult = false
    )

    check(
        "Negative segment should return false",
        result = isValidIPv4("192.-1.1.1"),
        correctResult = false
    )

    check(
        "Segment with leading zero should return false",
        result = isValidIPv4("192.168.01.1"),
        correctResult = false
    )

    check(
        "Single zero segment should return true",
        result = isValidIPv4("192.168.0.1"),
        correctResult = true
    )

    check(
        "Multiple zeros segment should return false",
        result = isValidIPv4("192.168.001.1"),
        correctResult = false
    )

    // Edge cases
    check(
        "Consecutive dots should return false",
        result = isValidIPv4("192..168.1.1"),
        correctResult = false
    )

    check(
        "Dot at start should return false",
        result = isValidIPv4(".192.168.1.1"),
        correctResult = false
    )

    check(
        "Dot at end should return false",
        result = isValidIPv4("192.168.1.1."),
        correctResult = false
    )

    check(
        "Non-numeric segments should return false",
        result = isValidIPv4("abc.def.ghi.jkl"),
        correctResult = false
    )

    check(
        "Mixed invalid segments should return false",
        result = isValidIPv4("192.a.1.1"),
        correctResult = false
    )

    // Whitespace tests
    check(
        "Leading and trailing whitespace should return true",
        result = isValidIPv4("  192.168.1.1  "),
        correctResult = true
    )

    check(
        "Middle whitespace should return false",
        result = isValidIPv4("192.168. 1.1"),
        correctResult = false
    )
}