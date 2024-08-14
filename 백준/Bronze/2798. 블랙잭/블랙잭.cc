#include <stdio.h>
#include <iostream>

using namespace std;

int main(void) {

	int card_N, card_M, arr[100]{}, check_value(0);

	cin >> card_N >> card_M;

	for (int i(0); i < card_N; i++) {
		cin >> arr[i];
	};

	for (int i(0); i < card_N - 2; i++) {
		for (int j(i + 1); j < card_N - 1; j++) {
			for (int k(j + 1); k < card_N; k++) {
				if (arr[i] + arr[j] + arr[k] <= card_M and check_value < arr[i] + arr[j] + arr[k])
					check_value = arr[i] + arr[j] + arr[k];
			};
		};
	};

	cout << check_value << endl;

	return 0;

}