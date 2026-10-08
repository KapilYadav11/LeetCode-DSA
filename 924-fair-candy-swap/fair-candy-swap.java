class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0;
        int sumB = 0;

        for (int x : aliceSizes) {
            sumA += x;
        }

        Set<Integer> setB = new HashSet<>();
        for (int y : bobSizes) {
            sumB += y;
            setB.add(y);
        }

        int delta = (sumB - sumA) / 2;

        for (int x : aliceSizes) {
            int y = x + delta;
            if (setB.contains(y)) {
                return new int[]{x, y};
            }
        }

        return new int[]{};
    }
}