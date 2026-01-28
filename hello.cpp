#include <bits/stdc++.h>
using namespace std;

bool isPalindrome(int x) {
    string s = to_string(x);
    string r = s;
    reverse(r.begin(), r.end());
    return s == r;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K;
    if (!(cin >> N >> K)) return 0;   // safety check

    int majesticCount = 0;

    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;

        // Already palindrome
        if (isPalindrome(x)) {
            majesticCount++;
            continue;
        }

        bool possible = false;
        for (int d = 1; d <= K; d++) {
            if (x - d >= 0 && isPalindrome(x - d)) {
                possible = true;
                break;
            }
            if (isPalindrome(x + d)) {
                possible = true;
                break;
            }
        }

        if (possible) majesticCount++;
    }

    cout << majesticCount;
    return 0;
}
