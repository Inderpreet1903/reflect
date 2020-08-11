package nz.co.trademe.tests.reponses;

import nz.co.trademe.tests.reponses.motorsResponseData.FoundCategories;
import nz.co.trademe.tests.reponses.motorsResponseData.List;

public class MotorsResponse {
    public int TotalCount;
    public int Page;
    public int PageSize;
    public List[] List;
    public FoundCategories[] FoundCategories;

}
