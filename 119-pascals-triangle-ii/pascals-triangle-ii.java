class Solution {
    public List<Integer> getRow(int rowIndex) {

        List<Integer> row = new ArrayList<>();

        // Initially, first element is always 1
        row.add(1);

        for (int i = 1; i <= rowIndex; i++) {

            // Update from right to left
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }

            // Last element of every row is always 1
            row.add(1);
        }

        return row;
    }
}