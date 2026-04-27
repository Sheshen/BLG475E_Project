# Manual Assessment: Black Box Testing Report

This document contains the Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA) for all 30 tasks in the dataset. It maps the base tests to the identified classes and boundary conditions to assess effectiveness, and proposes test mutations to cover the gaps.

---

## Task: `Java_1` (Separate Parentheses Groups)
**Description**: Separate balanced groups of nested parentheses into a list of strings, ignoring spaces.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Single balanced group (e.g., `()`) | Handled correctly |
| **EC2** | Valid | Multiple independent balanced groups | Handled correctly |
| **EC3** | Valid | Deeply nested balanced groups | Handled correctly |
| **EC4** | Valid | Input containing spaces (leading, trailing, inner) | Spaces ignored |
| **B1** | Boundary | Empty string `""` | Empty list `[]` |
| **B2** | Boundary | String with only spaces `"   "` | Empty list `[]` |
| **EC5** | Invalid | Unbalanced groups `(()` or `())` | Exception / Incorrect |
| **EC6** | Invalid | Null input | `NullPointerException` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_1_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `"(()()) ((())) () ((())()())"` | `["(()())", "((()))", "()", "((())()())"]` | EC2, EC3, EC4 | Good coverage of nesting & spaces |
| `"() (()) ((())) (((())))"` | `["()", "(())", "((()))", "(((())))"]` | EC2, EC3, EC4 | Good coverage of depth & spaces |
| `"(()(())((())))"` | `["(()(())((())))"]` | EC1, EC3 | Good coverage of deep single group |
| `"( ) (( )) (( )( ))"` | `["()", "(())", "(()())"]` | EC2, EC3, EC4 | Covers inner spaces well |

**Coverage Gap Analysis**: The base tests cover valid classes effectively but completely miss the boundary conditions (empty strings) and invalid classes.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty Input | `""` | `[]` | B1 |
| Only Spaces | `"   "` | `[]` | B2 |
| Null Input | `null` | Exception | EC6 |

---

## Task: `Java_3` (Below Zero)
**Description**: Detect if a bank account balance falls below zero given a list of operations.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | All positive operations | `false` |
| **EC2** | Valid | Balance dips below 0 | `true` |
| **EC3** | Valid | Balance stays >= 0 despite negative ops | `false` |
| **B1** | Boundary | Empty operations list | `false` |
| **B2** | Boundary | Balance hits exactly 0 | `false` |
| **B3** | Boundary | Balance dips to exactly -1 | `true` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_3_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `[]` | `false` | B1 | Covers empty list |
| `[1, 2, -3, 1, 2, -3]` | `false` | EC3, B2 | Covers exact 0 balance |
| `[1, 2, -4, 5, 6]` | `true` | EC2, B3 | Covers dipping to -1 |
| `[1, -1, 2, -2, 5, -5, 4, -4]` | `false` | EC3, B2 | Covers multiple exact 0 hits |
| `[1, -1, 2, -2, 5, -5, 4, -5]` | `true` | EC2, B3 | Covers dipping at the end |
| `[1, -2, 2, -2, 5, -5, 4, -4]` | `true` | EC2 | Covers dipping early |

**Coverage Gap Analysis**: Comprehensive, but misses a purely positive list.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Purely Positive | `[10, 20, 30]` | `false` | EC1 |
| Null Input | `null` | Exception | Invalid Handling |

---

## Task: `Java_6` (Parse Nested Parens)
**Description**: Output the deepest level of nesting for multiple groups of nested parentheses separated by spaces.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Single group | List of 1 element |
| **EC2** | Valid | Multiple groups separated by space | List of N elements |
| **EC3** | Valid | Varying depth of nesting | Correct depths |
| **B1** | Boundary | Empty string `""` | Empty list `[]` |
| **B2** | Boundary | Multiple spaces between groups | List of N elements |
| **B3** | Boundary | Flat group `()` | `1` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_6_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `"(()()) ((())) () ((())()())"` | `[2, 3, 1, 3]` | EC2, EC3, B3 | Good varied depths |
| `"() (()) ((())) (((())))"` | `[1, 2, 3, 4]` | EC2, EC3, B3 | Incremental depth |
| `"(()(())((())))"` | `[4]` | EC1, EC3 | Deep single group |

**Coverage Gap Analysis**: Base tests cover the standard scenarios well. However, they lack boundary testing for empty inputs and irregular spacing.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty String | `""` | `[]` | B1 |
| Extra Spaces | `"()   (())"` | `[1, 2]` | B2 |

---

## Task: `Java_7` (Filter By Substring)
**Description**: Filter an input list of strings for ones containing a given substring.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | List contains substring in some items | Filtered list |
| **EC2** | Valid | Substring is empty `""` | Returns original list |
| **EC3** | Valid | Substring matches exact item | Returns item |
| **B1** | Boundary | Empty list | Empty list `[]` |
| **B2** | Boundary | No items contain substring | Empty list `[]` |
| **B3** | Boundary | All items contain substring | Returns original list |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_7_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `[], "john"` | `[]` | B1 | Correctly tests empty list |
| `["xxx", "asd", "xxy", ...], "xxx"` | `["xxx", "xxxAAA", "xxx"]` | EC1, EC3 | Exact matches & embedded |
| `["xxx", "asd", "aaaxxy", ...], "xx"` | `["xxx", "aaaxxy", "xxxAAA", "xxx"]` | EC1 | Partial embedded matches |
| `["grunt", "trumpet", "prune", ...], "run"` | `["grunt", "prune"]` | EC1 | Standard behavior |

**Coverage Gap Analysis**: Missing boundary case where substring is empty, or no items match (with a non-empty list), or all items match.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Inputs | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty Substring | `["a", "b"], ""` | `["a", "b"]` | EC2 |
| None Match | `["a", "b"], "z"` | `[]` | B2 |
| All Match | `["ab", "ac"], "a"` | `["ab", "ac"]` | B3 |

---

## Task: `Java_9` (Rolling Max)
**Description**: Generate a list of the rolling maximum elements found until a given moment in a sequence.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Strictly increasing sequence | Identical to input |
| **EC2** | Valid | Strictly decreasing sequence | Array of the first element |
| **EC3** | Valid | Mixed sequence | Correct rolling max |
| **B1** | Boundary | Empty sequence `[]` | `[]` |
| **B2** | Boundary | Sequence with 1 element | List of 1 element |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_9_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `[]` | `[]` | B1 | Empty sequence |
| `[1, 2, 3, 4]` | `[1, 2, 3, 4]` | EC1 | Strictly increasing |
| `[4, 3, 2, 1]` | `[4, 4, 4, 4]` | EC2 | Strictly decreasing |
| `[3, 2, 3, 100, 3]` | `[3, 3, 3, 100, 100]` | EC3 | Mixed |

**Coverage Gap Analysis**: The test suite provides excellent coverage across all major equivalence classes. Adding a single element test is the only minor missing edge case.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Single element | `[5]` | `[5]` | B2 |

---

## Task: `Java_10` (Make Palindrome)
**Description**: Find the shortest palindrome that begins with a supplied string.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | String is already a palindrome | Original string |
| **EC2** | Valid | Needs 1 char to be palindrome | Correct string |
| **EC3** | Valid | Needs multiple chars | Correct string |
| **B1** | Boundary | Empty string `""` | `""` |
| **B2** | Boundary | Single character `"x"` | `"x"` |
| **B3** | Boundary | Long string with no repeating chars | `string + reverse(string[0:n-1])` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_10_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `""` | `""` | B1 | Empty string |
| `"x"` | `"x"` | B2 | Single character |
| `"xyz"` | `"xyzyx"` | EC3, B3 | No repeating characters |
| `"xyx"` | `"xyx"` | EC1 | Already palindrome |
| `"jerry"` | `"jerryrrej"` | EC3 | Palindromic suffix "rr" ? No, suffix "y". |

**Coverage Gap Analysis**: Solid coverage. A slight gap on palindromic suffixes longer than 1 character.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Long Palindrome Suffix | `"abcaba"` | `"abcabacba"` | Suffix Optimization |

---

## Task: `Java_11` (String XOR)
**Description**: Perform binary XOR on two strings consisting only of 1s and 0s.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Mixed 1s and 0s, different | XOR'ed result |
| **EC2** | Valid | Both strings all 0s | All 0s |
| **EC3** | Valid | Both strings all 1s | All 0s |
| **B1** | Boundary | Single character | Single char XOR |
| **B2** | Boundary | Empty strings | `""` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_11_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `"111000", "101010"` | `"010010"` | EC1 | Mixed strings |
| `"1", "1"` | `"0"` | B1, EC3 | Single char |
| `"0101", "0000"` | `"0101"` | EC1 | Identity XOR |

**Coverage Gap Analysis**: Missing empty string boundary.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty strings | `"", ""` | `""` | B2 |

---

## Task: `Java_13` (Greatest Common Divisor)
**Description**: Return the greatest common divisor of two integers.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Co-prime numbers | `1` |
| **EC2** | Valid | One is multiple of another | The smaller number |
| **EC3** | Valid | Share common divisors > 1 | GCD |
| **B1** | Boundary | `a = 0` or `b = 0` | The non-zero number |
| **B2** | Boundary | Both prime numbers | `1` |
| **B3** | Boundary | `a = b` | `a` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_13_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `3, 7` | `1` | EC1, B2 | Co-primes / primes |
| `10, 15` | `5` | EC3 | Standard GCD |
| `49, 14` | `7` | EC3 | Standard GCD |
| `144, 60` | `12` | EC3 | Standard GCD |

**Coverage Gap Analysis**: Does not cover `0`, multiples, or identical numbers.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| One is zero | `0, 5` | `5` | B1 |
| Multiples | `10, 20` | `10` | EC2 |
| Identical | `5, 5` | `5` | B3 |

---

## Task: `Java_17` (Parse Music)
**Description**: Parse musical notes and return a list of integers corresponding to beats. `o` = 4, `o|` = 2, `.|` = 1.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | All `o` notes | `[4, 4...]` |
| **EC2** | Valid | All `o|` notes | `[2, 2...]` |
| **EC3** | Valid | All `.|` notes | `[1, 1...]` |
| **EC4** | Valid | Mixed notes | Mixed integers |
| **B1** | Boundary | Empty string `""` | `[]` |
| **B2** | Boundary | Extraneous spaces | Parsed correctly |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_17_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `""` | `[]` | B1 | Empty string |
| `"o o o o"` | `[4, 4, 4, 4]` | EC1 | Single type |
| `".| .| .| .|"` | `[1, 1, 1, 1]` | EC3 | Single type |
| `"o| o| .| .| o o o o"` | `[2, 2, 1, 1, 4, 4, 4, 4]` | EC4 | Mixed |
| `"o| .| o| .| o o| o o|"` | `[2, 1, 2, 1, 4, 2, 4, 2]` | EC4 | Mixed |

**Coverage Gap Analysis**: Excellent coverage of valid classes and empty boundary. Missing irregular spacing boundary.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Irregular Spaces | `"o    o|"` | `[4, 2]` | B2 |

---

## Task: `Java_18` (How Many Times)
**Description**: Count how many times a substring can be found in the original string, including overlapping cases.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Non-overlapping matches | Count |
| **EC2** | Valid | Overlapping matches | Count including overlap |
| **EC3** | Valid | No matches | `0` |
| **B1** | Boundary | Empty string | `0` |
| **B2** | Boundary | Empty substring | Undefined / Error / Infinite |
| **B3** | Boundary | Substring > String | `0` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_18_test.java`*

| Base Test Input | Expected Output | Covered Classes | Effectiveness |
| :--- | :--- | :--- | :--- |
| `"", "x"` | `0` | B1 | Empty string |
| `"xyxyxyx", "x"` | `4` | EC1 | Non-overlapping |
| `"cacacacac", "cac"` | `4` | EC2 | Overlapping |
| `"john doe", "john"` | `1` | EC1 | Single match |

**Coverage Gap Analysis**: Great coverage of overlap. Misses empty substring boundary and substring > string.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Sub > String | `"x", "xx"` | `0` | B3 |
| Empty sub | `"abc", ""` | Error/0 | B2 |

---

## Task: `Java_19` (Sort Numbers)
**Description**: Sort a space-delimited string of number words ('zero' to 'nine') from smallest to largest.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Mixed unsorted numbers | Sorted string |
| **EC2** | Valid | Already sorted numbers | Original string |
| **EC3** | Valid | Contains duplicate numbers | Sorted with duplicates |
| **B1** | Boundary | Empty string `""` | `""` |
| **B2** | Boundary | Single number word | Original string |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_19_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `"three one five"` | EC1 | Standard case |
| `"zero zero"` | EC3 | Duplicate words |
| `""` | B1 | Empty string handled correctly |

**Coverage Gap Analysis**: The tests generally cover standard usage and the empty boundary. Single number boundaries and already sorted boundaries are often implicitly covered but could be explicit.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Single number | `"five"` | `"five"` | B2 |
| Already sorted | `"one two three"` | `"one two three"` | EC2 |

---

## Task: `Java_20` (Find Closest Elements)
**Description**: Find the two elements in a list of numbers that are closest to each other and return them in ascending order.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Distinct numbers with one unique min distance | Closest pair |
| **EC2** | Valid | Multiple pairs with the same distance | First/any closest pair |
| **EC3** | Valid | Duplicate numbers (distance = 0) | The duplicate pair |
| **B1** | Boundary | Exactly 2 elements | Those 2 elements |
| **B2** | Boundary | List with negative numbers | Closest pair |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_20_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[1.0, 2.0, 3.0, 4.0, 5.0, 2.2]` | EC1 | Standard array with unique min |
| `[1.0, 2.0, 3.0, 4.0, 5.0, 2.0]` | EC3 | Duplicates (distance 0) |

**Coverage Gap Analysis**: Misses negative numbers and lists with exactly 2 elements.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Negative numbers | `[-5.0, -1.0, 0.0, 0.5]` | `[0.0, 0.5]` | B2 |
| Minimal list | `[10.0, 1.0]` | `[1.0, 10.0]` | B1 |

---

## Task: `Java_24` (Largest Divisor)
**Description**: Find the largest number that divides `n` evenly, smaller than `n`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | `n` is a composite number | Largest factor > 1 |
| **EC2** | Valid | `n` is a prime number | `1` |
| **EC3** | Valid | `n` is a perfect square | `sqrt(n)` or multiple |
| **B1** | Boundary | `n = 2` (smallest valid prime) | `1` |
| **B2** | Boundary | `n = 4` (smallest valid composite)| `2` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_24_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `15` | EC1 | Composite number |

**Coverage Gap Analysis**: The dataset test is extremely limited. It only tests one composite number. It completely ignores primes and boundary constraints.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Prime number | `7` | `1` | EC2 |
| Smallest Composite | `4` | `2` | B2 |
| Perfect Square | `49` | `7` | EC3 |

---

## Task: `Java_25` (Factorize)
**Description**: Return a list of prime factors of a given integer in order.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | `n` is prime | `[n]` |
| **EC2** | Valid | `n` has distinct prime factors | `[p1, p2, p3]` |
| **EC3** | Valid | `n` has repeating prime factors | `[p1, p1, p2]` |
| **B1** | Boundary | `n = 2` (smallest prime) | `[2]` |
| **B2** | Boundary | `n = 1` (no prime factors) | `[]` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_25_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `8` | EC3 | Repeating prime factors |
| `25` | EC3 | Repeating prime factors |
| `70` | EC2 | Distinct prime factors |

**Coverage Gap Analysis**: Good coverage of composite numbers, but entirely misses prime numbers as inputs and boundary cases like `1` or `2`.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Prime Number | `13` | `[13]` | EC1 |
| Smallest Prime | `2` | `[2]` | B1 |
| Boundary 1 | `1` | `[]` | B2 |

---

## Task: `Java_27` (Flip Case)
**Description**: Flip lowercase characters to uppercase and uppercase to lowercase.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | All lowercase | All uppercase |
| **EC2** | Valid | All uppercase | All lowercase |
| **EC3** | Valid | Mixed casing | Flipped mixed casing |
| **EC4** | Valid | String with numbers/symbols | Case flipped, symbols untouched|
| **B1** | Boundary | Empty string `""` | `""` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_27_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `"Hello"` | EC3 | Mixed case |
| `""` | B1 | Empty string |

**Coverage Gap Analysis**: Only basic mixed-case and empty strings are tested. No tests for symbols/numbers.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| With Symbols | `"aB1@c"` | `"Ab1@C"` | EC4 |
| All Lowercase | `"abc"` | `"ABC"` | EC1 |

---

## Task: `Java_31` (Is Prime)
**Description**: Return true if a given number is prime.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Prime numbers | `true` |
| **EC2** | Valid | Composite numbers | `false` |
| **B1** | Boundary | `n = 0` | `false` |
| **B2** | Boundary | `n = 1` | `false` |
| **B3** | Boundary | `n < 0` | `false` |
| **B4** | Boundary | `n = 2` | `true` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_31_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `6`, `4` | EC2 | Small composites |
| `101`, `11`, `13441`, `61` | EC1 | Various primes |
| `1` | B2 | Edge case 1 |

**Coverage Gap Analysis**: Excellent coverage of primes, composites, and 1. Lacks tests for negative numbers and the edge case `2`.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| First Prime | `2` | `true` | B4 |
| Negative Number| `-7` | `false` | B3 |
| Zero | `0` | `false` | B1 |

---

## Task: `Java_32` (Find Zero)
**Description**: Find the root (zero) of a polynomial given its coefficients.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Linear polynomial (2 coeffs) | `-c0 / c1` |
| **EC2** | Valid | Cubic polynomial (4 coeffs) | Root |
| **B1** | Boundary | Exactly 2 coefficients | Exact computation |
| **B2** | Boundary | Root is `0` | `0` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_32_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[1, 2]` | EC1, B1 | Linear polynomial root is -0.5 |
| `[-6, 11, -6, 1]` | EC2 | Cubic polynomial |

**Coverage Gap Analysis**: Covers standard linear and cubic scenarios.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Root is Zero | `[0, 1]` | `0.0` | B2 |

---

## Task: `Java_36` (Fizz Buzz)
**Description**: Count how many times digit 7 appears in integers less than `n` which are divisible by 11 or 13.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | `n` contains multiple matches | Count |
| **EC2** | Valid | `n` contains no matches | `0` |
| **B1** | Boundary | `n <= 0` | `0` |
| **B2** | Boundary | `n` hits exactly a multiple (e.g., `78`) | Check strict inequality `<` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_36_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `50` | EC2 | 0 |
| `78` | EC1, B2 | Exact multiple of 13 containing 7, tests strict `<` vs `<=` |
| `79` | EC1 | Counts 77 and others |

**Coverage Gap Analysis**: Excellent boundary testing explicitly testing `78` vs `79`. Missing negative boundaries.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Negative Number | `-10` | `0` | B1 |

---

## Task: `Java_39` (Prime Fib)
**Description**: Returns the n-th Fibonacci number that is also prime.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Small `n` (`1-3`) | `2, 3, 5` |
| **EC2** | Valid | Large `n` (`4-5`) | `13, 89` |
| **B1** | Boundary | `n = 1` | `2` |
| **B2** | Boundary | `n <= 0` | Undefined/Error |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_39_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `1, 2, 3` | EC1, B1 | Tests sequence initiation |
| `4, 5` | EC2 | Tests continuation and skipping composite Fibs |

**Coverage Gap Analysis**: Missing invalid values (`n <= 0`).

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Invalid `n` | `0` | Exception | B2 |

---

## Task: `Java_40` (Triples Sum To Zero)
**Description**: Return true if there are three distinct elements in the list that sum to zero.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Contains a valid triple | `true` |
| **EC2** | Valid | Does not contain valid triple | `false` |
| **EC3** | Valid | Contains zeroes | Computed correctly |
| **B1** | Boundary | List has exactly 3 items | True/False depending on sum |
| **B2** | Boundary | List has < 3 items | `false` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_40_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[1, 3, 5, 0]` | EC2, EC3 | No sum, with zero |
| `[1, 3, -2, 1]` | EC1 | Valid sum |
| `[1]` | B2 | Less than 3 items |
| `[2, 4, -5, 3, 9, 7]` | EC1 | Disconnected valid sum |

**Coverage Gap Analysis**: Excellent coverage of size constraints and 0-elements.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Exact 3 elements | `[-1, 0, 1]` | `true` | B1 |
| Exact 3 (False)| `[1, 2, 3]` | `false` | B1 |

---

## Task: `Java_48` (Is Palindrome)
**Description**: Check if a given string is a palindrome.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Even length palindrome | `true` |
| **EC2** | Valid | Odd length palindrome | `true` |
| **EC3** | Valid | Not a palindrome | `false` |
| **B1** | Boundary | Empty string `""` | `true` |
| **B2** | Boundary | Single character `"a"` | `true` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_48_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `"aba"` | EC2 | Odd palindrome |
| `"ab"` | EC3 | Not a palindrome |
| `""` | B1 | Empty boundary |

**Coverage Gap Analysis**: Good coverage of basic cases. Misses even-length palindromes.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Even Palindrome | `"abba"` | `true` | EC1 |
| Single Char | `"z"` | `true` | B2 |

---

## Task: `Java_51` (Remove Vowels)
**Description**: Return string without vowels.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | String with only vowels | `""` |
| **EC2** | Valid | String with no vowels | Original string |
| **EC3** | Valid | Mixed characters | Vowels removed |
| **B1** | Boundary | Empty string `""` | `""` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_51_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `"abcdef\nghijklm"` | EC3 | Mixed chars |
| `""` | B1 | Empty string |

**Coverage Gap Analysis**: Covers basic scenarios. Missing edge cases with all vowels or no vowels.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| All Vowels | `"aeiouAEIOU"` | `""` | EC1 |
| No Vowels | `"bcdfg"` | `"bcdfg"` | EC2 |

---

## Task: `Java_52` (Below Threshold)
**Description**: Return true if all elements in the list are below a given threshold `t`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | All elements strictly `< t` | `true` |
| **EC2** | Valid | Some elements `> t` | `false` |
| **B1** | Boundary | At least one element `== t` | `false` |
| **B2** | Boundary | Empty list | `true` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_52_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[1, 2, 4, 10], 100` | EC1 | All strictly below |
| `[1, 20, 4, 10], 5` | EC2 | Some above |

**Coverage Gap Analysis**: Completely misses the exact boundary `== t` and empty list cases.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Exact Threshold | `[1, 2, 5], 5` | `false` | B1 |
| Empty List | `[], 10` | `true` | B2 |

---

## Task: `Java_59` (Largest Prime Factor)
**Description**: Return the largest prime factor of a given number.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Composite number | Largest prime |
| **EC2** | Valid | Prime number | The number itself |
| **B1** | Boundary | Smallest prime `2` | `2` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_59_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `2048` | EC1 | Only factor is 2 |
| `13195` | EC1 | Multiple factors |

**Coverage Gap Analysis**: Misses prime numbers and boundary smallest primes.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Prime Number | `17` | `17` | EC2 |
| Smallest Prime | `2` | `2` | B1 |

---

## Task: `Java_63` (Fibfib)
**Description**: Calculate Fibfib sequence `f(n) = f(n-1) + f(n-2) + f(n-3)`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | `n >= 3` | Computed sum |
| **B1** | Boundary | `n = 0` | `0` |
| **B2** | Boundary | `n = 1` | `0` |
| **B3** | Boundary | `n = 2` | `1` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_63_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `n=1`, `n=2` | B2, B3 | Boundary cases |
| `n=5`, `n=8` | EC1 | Standard recursive calls |

**Coverage Gap Analysis**: Excellent coverage.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Boundary 0 | `0` | `0` | B1 |

---

## Task: `Java_65` (Circular Shift)
**Description**: Circular shift the digits of an integer `x` by `shift`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | `shift` < `len(x)` | Shifted string |
| **EC2** | Valid | `shift` > `len(x)` | Reversed string |
| **B1** | Boundary | `shift == 0` | Original string |
| **B2** | Boundary | `shift == len(x)` | Reversed string |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_65_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `12, 1` | EC1 | Normal shift |
| `12, 2` | B2 | Shift equals length |
| `12, 3` | EC2 | Shift greater than length |

**Coverage Gap Analysis**: Solid boundary coverage. Misses zero-shift boundary.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Zero Shift | `123, 0` | `"123"` | B1 |

---

## Task: `Java_67` (Fruit Distribution)
**Description**: Find remaining fruits given a string containing apples/oranges counts and total fruits `n`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | String has non-zero apples/oranges | `n - (apples+oranges)` |
| **B1** | Boundary | 0 apples or oranges | Handled |
| **B2** | Boundary | `n` == apples + oranges | `0` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_67_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `"5 apples and 6 oranges", 19` | EC1 | Standard subtraction |
| `"0 apples and 1 oranges", 3` | B1 | Zero count |

**Coverage Gap Analysis**: Good coverage of zero values. Misses boundary where remainder is exactly 0.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Zero Remainder | `"5 apples 5 oranges", 10` | `0` | B2 |

---

## Task: `Java_68` (Pluck)
**Description**: Return `[value, index]` of the smallest even value in the list.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Single even number | `[val, idx]` |
| **EC2** | Valid | Multiple evens, unique min | `[min_val, idx]` |
| **EC3** | Valid | No even numbers | `[]` |
| **B1** | Boundary | Empty list | `[]` |
| **B2** | Boundary | Multiple identical minimum evens | Smallest index returned |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_68_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[4, 2, 3]` | EC2 | Standard min |
| `[1, 2, 3]` | EC1 | Single even |
| `[1, 3, 5]` | EC3 | No evens |

**Coverage Gap Analysis**: Misses tie-breaker index requirement and empty lists.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty List | `[]` | `[]` | B1 |
| Tie Breaker | `[4, 2, 3, 2]` | `[2, 1]` | B2 |

---

## Task: `Java_69` (Search)
**Description**: Find the greatest integer whose frequency is `>=` its value.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Integer meets criteria | Max integer |
| **EC2** | Valid | No integer meets criteria | `-1` |
| **B1** | Boundary | Empty list | `-1` |
| **B2** | Boundary | Frequency strictly equals value | Integer |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_69_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[4, 1, 2, 2, 3, 1]` | EC1, B2 | Exact frequency matches |
| `[1, 2, 2, 3, 3, 3, 4, 4, 4]` | EC2 | No match for 4, highest is 3 |

**Coverage Gap Analysis**: Great coverage of exact frequencies. Misses empty list.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Empty list | `[]` | `-1` | B1 |

---

## Task: `Java_70` (Strange Sort List)
**Description**: Sort list: `[min, max, 2nd min, 2nd max, ...]`.

### 1. Equivalence Classes & Boundary Conditions
| ID | Type | Description | Expected Outcome |
| :--- | :--- | :--- | :--- |
| **EC1** | Valid | Even length list | Properly ordered |
| **EC2** | Valid | Odd length list | Properly ordered |
| **EC3** | Valid | List with duplicates | Properly ordered |
| **B1** | Boundary | Empty list `[]` | `[]` |
| **B2** | Boundary | Single element list | `[element]` |

### 2. Base Test Coverage Assessment
*Evaluated against `tests/Java_70_test.java`*

| Base Test Scenario | Covered Classes | Effectiveness |
| :--- | :--- | :--- |
| `[1, 2, 3, 4]` | EC1 | Even length |
| `[5, 5, 5, 5]` | EC3 | Duplicates |
| `[]` | B1 | Empty list |

**Coverage Gap Analysis**: Covers most primary cases including empty list. Could explicitly test single element.

### 3. Proposed New Tests (Mutations)
| Mutation Type | New Test Input | Expected Output | Target |
| :--- | :--- | :--- | :--- |
| Single element | `[7]` | `[7]` | B2 |
| Odd Length | `[1, 2, 3]` | `[1, 3, 2]` | EC2 |
