package com.management.utils;

import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * String utility class
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    /** null character string */
    private static final String NULLSTR = "";

    /** underline */
    private static final char SEPARATOR = '_';

    /**
     * Gets a parameter that is not null
     * 
     * @param value defaultValue The value to be judged
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue)
    {
        return value != null ? value : defaultValue;
    }

    /**
     * * Determines whether a Collection is empty, including List, Set, and Queue
     * 
     * @param coll Collection to evaluate
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * Determines whether a Collection is not empty, including List, Set, and Queue
     * 
     * @param coll Collection to evaluate
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * * Determines whether an array of objects is empty
     * 
     * @param objects An array of objects to judge
     ** @return true: empty false: not empty
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * Determines whether an array of objects is not empty
     * 
     * @param objects An array of objects to judge
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * Determines whether a Map is empty
     * 
     * @param map Map to judge
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * Determines whether a Map is not empty
     * 
     * @param map Map to judge
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * * Determines whether a string is an empty string
     * 
     * @param str String
     * @return true: not empty false: empty
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * Determines whether a string is not an empty string
     * 
     * @param str String
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * Determines whether an object is empty
     * 
     * @param object Object
     * @return true: empty false: not empty
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * Determines whether an object is not empty
     * 
     * @param object Object
     * @return true: not empty false: empty
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * * Determine whether an object is an array type (array of Java basic types)
     * 
     * @param object Object
     * @return true: is an array false: is not an array
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * remove spaces in the string
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }

    /**
     * Intercept string
     * 
     * @param str string
     * @param start start
     * @return result
     */
    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = str.length() + start;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * Intercept string
     * 
     * @param str string
     * @param start start
     * @param end end
     * @return result
     */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * String to set
     * 
     * @param str string
     * @param sep separator
     * @return set collection
     */
    public static final Set<String> str2Set(String str, String sep)
    {
        return new HashSet<String>(str2List(str, sep, true, false));
    }

    /**
     * String to list
     * 
     * @param str string
     * @param sep separator
     * @param filterBlank Filtered blank
     * @param trim Remove the beginning and end white space
     * @return list collection
     */
    public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim)
    {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(str))
        {
            return list;
        }

        // Filter blank string
        if (filterBlank && StringUtils.isBlank(str))
        {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split)
        {
            if (filterBlank && StringUtils.isBlank(string))
            {
                continue;
            }
            if (trim)
            {
                string = string.trim();
            }
            list.add(string);
        }

        return list;
    }

    /**
     * Finds whether the specified string contains any string in the specified string list while ignoring case
     *
     * @param cs Specified string
     * @param searchCharSequences An array of strings to check
     * @return Whether to contain any string
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences)
    {
        if (isEmpty(cs) || isEmpty(searchCharSequences))
        {
            return false;
        }
        for (CharSequence testStr : searchCharSequences)
        {
            if (containsIgnoreCase(cs, testStr))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * The humps are named with underscores
     */
    public static String toUnderScoreCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // Whether the prefix character is uppercase
        boolean preCharIsUpperCase = true;
        // Whether the current character is uppercase
        boolean curreCharIsUpperCase = true;
        // Whether the next character is capitalized
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (i > 0)
            {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            }
            else
            {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1))
            {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Contains or not string
     * 
     * @param str Validation string
     * @param strs String groups
     * @return Inclusion returns true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Converts a string named in underscore uppercase to hump. If the string named in underscore uppercase before conversion is empty, an empty string is returned. For example, HELLO_WORLD->HelloWorld
     * 
     * @param name The string is capitalized by the underscore before conversion
     * @return Converted hump-named string
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // Quick Check
        if (name == null || name.isEmpty())
        {
            // No need to convert
            return "";
        }
        else if (!name.contains("_"))
        {
            // There is no underline and only the first letter is capitalized
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        //Split the original string with underscores
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // Skip downlining or double underlining at the beginning or end of the original string
            if (camel.isEmpty())
            {
                continue;
            }
            // capitalize the first letter
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * Hump naming Example: user_name->userName
     */
    public static String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == SEPARATOR)
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Finds whether the specified string matches any string in the specified string list
     * 
     * @param str Specified string
     * @param strs An array of strings to check
     * @return whether it matches
     */
    public static boolean matches(String str, List<String> strs)
    {
        if (isEmpty(str) || isEmpty(strs))
        {
            return false;
        }
        for (String pattern : strs)
        {
            if (isMatch(pattern, str))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the url is configured with the rule:
     * ? Represents a single character;
     * * Represents any string in the path of one layer, and cannot cross layers;
     * ** Represents any layer path;
     * 
     * @param pattern Matching Rules
     * @param url url to be matched
     * @return
     */
    public static boolean isMatch(String pattern, String url)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }
}