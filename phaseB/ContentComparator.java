public interface ContentComparator {

    /**
     *
     * @return the result of comparing two file strings parsed from the abstract syntax tree.
     */
    public String fileStringCompare(String fileOne, String fileTwo);

    /**
     * Add the result of the file String comparison to the report to be generated.
     * @param analysis the result of comparing two file strings.
     */
    public void addComparisonToReport(String analysis);

}
