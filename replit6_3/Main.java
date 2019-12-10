package replit6_3;

class Main {
    public static int[] merge(int[] arr1, int[] arr2) {
        // fill here!
        int length = arr1.length + arr2.length;
        int[] arr = new int[length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < length - 1; i++) {
            if (j < arr1.length - 1 || k < arr2.length - 1) {
                if (arr1[j] < arr2[k]) {
                    arr[i] = arr1[j];
                    if (j != arr1.length - 1)
                        j++;
                }
                else if (arr1[j] > arr2[k]) {
                    arr[i] = arr2[k];
                    if (k != arr2.length - 1)
                        k++;
                }
                else if (arr1[j] == arr2[k]) {
                    arr[i] = arr1[j];
                    j++;
                }
            }
            else {
                if (arr1[j] < arr2[k]) {
                    arr[i] = arr1[j];
                    arr[length - 1] = arr2[k];
                }
                else if (arr1[j] > arr2[k]) {
                    arr[i] = arr2[k];
                    arr[length - 1] = arr1[j];
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 8, 16, 20, 40};
        int[] arr2 = {3, 6, 9, 12, 60, 61};
        int[] merged = merge(arr1, arr2);
        for (int i = 0 ; i < merged.length; i++) {
            System.out.println(merged[i]);
            // 1 2 3 4 6 8 9 12 16
        }
    }
}



/*
이미 오름차순으로 정렬되어 있는 두 정수 배열을 받아서,
마찬가지로 오름차순 정렬된 합쳐진 배열을 반환하는 함수를 작성하라.

참고: 아직 배우지 않은 정렬을 사용하지 않고 작성할 수 있음.
 */