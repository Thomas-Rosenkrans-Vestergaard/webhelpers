package tvestergaard.webhelpers.parameters;

import java.util.regex.Pattern;

public class InputText extends AbstractInput
{

	/**
	 * The value of the input.
	 */
	private final String value;

	/**
	 * The length of the input.
	 */
	private final int length;

	/**
	 * Creates a new {@link InputText}.
	 *
	 * @param name  The name of the input.
	 * @param value The value of the input.
	 */
	public InputText(String name, String value)
	{
		super(name);

		this.value = value;
		this.length = value.length();
	}

	/**
	 * Returns {@code true} if the value is empty. This method has the same behavior as calling
	 * {@link InputText#length(int)}} where {@code n = 0}.
	 *
	 * @return {@true} if the value is empty. {@code false} in all other cases.
	 */
	public boolean empty()
	{
		return length(0);
	}

	/**
	 * Checks that the length of the value equals the provided {@code int}.
	 *
	 * @param n The {@code int} the length of the value must equal.
	 *
	 * @return {@code true} if the length of the value equals the provided {@code int}. Returns {@code false} in all
	 * other cases.
	 */
	public boolean length(int n)
	{
		return this.length == n;
	}

	/**
	 * Checks that the length of the value is smaller than the provided {@code int}.
	 *
	 * @param n The {@code int} the length of the value must be smaller than.
	 *
	 * @return {@code true} if the length of the value equals is smaller than the provided {@code int}.
	 */
	public boolean shorter(int n)
	{
		return this.length < n;
	}

	/**
	 * Checks that the length of the value is greater than the provided {@code int}.
	 *
	 * @param n The {@code int} the length of the value must be greater than.
	 *
	 * @return {@code true} if the length of the value equals is greater than the provided {@code int}.
	 */
	public boolean longer(int n)
	{
		return this.length > n;
	}

	/**
	 * Checks that the value matches the provided {@code pattern}.
	 *
	 * @param pattern The pattern that the value must match.
	 *
	 * @return {@code true} if the {@code pattern} matches, {@code false} in all other cases.
	 */
	public boolean matches(Pattern pattern)
	{
		return pattern.matcher(this.value).matches();
	}

	/**
	 * Checks that the value matches the provided {@code String} exactly using the {@link String#equals(Object)} method.
	 *
	 * @param other The other {@code String} that the value must equal.
	 *
	 * @return {@code true} if the value matches the provided {@code String}, {@code false} in all other cases.
	 */
	public boolean matches(String other)
	{
		return this.value.equals(other);
	}

	/**
	 * Checks that the value matches at least one of the provided {@code String} instances.
	 *
	 * @param others The {@code String} instances to check against.
	 *
	 * @return {@code true} if the value equals at least one of the provided {@code others}, {@code false} in all
	 * other cases.
	 */
	public boolean in(Iterable<? extends String> others)
	{
		for (String other : others) {
			if (this.value.equals(other)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks that the value matches at least one of the provided {@code String} instances.
	 *
	 * @param others The {@code String} instances to check against.
	 *
	 * @return {@code true} if the value equals at least one of the provided {@code others}, {@code false} in all
	 * other cases.
	 */
	public boolean in(String[] others)
	{
		for (String other : others) {
			if (this.value.equals(other)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks that the provided {@code CharSequence} is contained within the value.
	 *
	 * @param other The {@code CharSequence} the value must contain.
	 *
	 * @return {@code true} if the provided {@code CharSequence} is contained within the value, {@code false} in all
	 * other cases.
	 */
	public boolean contains(CharSequence other)
	{
		return this.value.contains(other);
	}

	/**
	 * Checks that all the provided {@code CharSequence} instances are contained within the value.
	 *
	 * @param others The other {@code CharSequence} instances the value must contain.
	 *
	 * @return {@code true} if all the provided {@code CharSequence} instances are contained within the value, {@code
	 * false} in all other cases.
	 */
	public boolean containsAll(Iterable<? extends CharSequence> others)
	{
		for (CharSequence other : others) {
			if (!this.value.contains(other)) {
				return false;
			}
		}

		return true;
	}


	/**
	 * Checks that all the provided {@code CharSequence} instances are contained within the value.
	 *
	 * @param others The other {@code CharSequence} instances the value must contain.
	 *
	 * @return {@code true} if all the provided {@code CharSequence} instances are contained within the value, {@code
	 * false} in all other cases.
	 */
	public boolean containsAll(CharSequence[] others)
	{
		for (CharSequence other : others) {
			if (!this.value.contains(other)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks that any of the provided {@code CharSequence} instances are contained within the value.
	 *
	 * @param others The other {@code CharSequence} instances the value must contain at least one of.
	 *
	 * @return {@code true} if any of the provided {@code CharSequence} instances are contained within the value, {@code
	 * false} in all other cases.
	 */
	public boolean containsAny(Iterable<? extends CharSequence> others)
	{
		for (CharSequence other : others) {
			if (this.value.contains(other)) {
				return true;
			}
		}

		return false;
	}


	/**
	 * Checks that any of the provided {@code CharSequence} instances are contained within the value.
	 *
	 * @param others The other {@code CharSequence} instances the value must contain at least one of.
	 *
	 * @return {@code true} if any of the provided {@code CharSequence} instances are contained within the value, {@code
	 * false} in all other cases.
	 */
	public boolean containsAny(CharSequence[] others)
	{
		for (CharSequence other : others) {
			if (this.value.contains(other)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the internal value of the {@code InputText}.
	 *
	 * @return The internal value of the {@code InputText}.
	 */
	public String getValue()
	{
		return this.value;
	}
}
