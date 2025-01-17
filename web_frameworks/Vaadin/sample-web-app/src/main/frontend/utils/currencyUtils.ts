/**
 * Formats a number into a currency string.
 *
 * @param {number} amount - The number to format.
 * @param {string} [currency='USD'] - The currency code (default is USD).
 * @param {string} [locale='en-US'] - The locale code (default is en-US).
 * @returns {string} - The formatted currency string.
 */
export const formatCurrency = (
    value: number | null | undefined,
    currency: string = "USD",
    locale: string = "en-US",
): string => {
    if (value == null) return ""; // Return an empty string for null or undefined values

    const options: Intl.NumberFormatOptions = {
        style: "currency" as const, // Use 'as const' to ensure TypeScript recognizes it as a valid style
        currency,
        minimumFractionDigits: 0, // No decimal places
        maximumFractionDigits: 0, // No decimal places
    };

    return new Intl.NumberFormat(locale, options).format(value);
};
