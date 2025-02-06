package ui.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static ui.constants.Tags.CAREERS_PAGE_TESTS;

@Tag(CAREERS_PAGE_TESTS)
class CareersTests extends TestBase {

    @BeforeEach
    void beforeEach() {
        careersPage.openCareersPage()
                .acceptCookie();
    }

    @Test
    @DisplayName("Filtering jobs by location should update job count and allow clearing filters")
    void filteringJobsShouldUpdateJobsCountAndBeResettableTest() {
        var jobsCountBeforeFiltering = careersPage.getStartJobCount();

        careersPage.verifyClearFiltersButtonNotVisible()
                .applyLocationFilter("Serbia, Vojvodina, Belgrade")
                .checkJobResultsCountShouldBeLessThan(jobsCountBeforeFiltering)
                .clearFilters()
                .verifyJobCountIs(jobsCountBeforeFiltering);
    }

    @Test
    @DisplayName("Check that job title remains the same after opening the job details page")
    void nameOfJobShouldBeTheSameAfterOpeningTest() {
        var expectedJobName = careersPage.getNameFromFirstJob();
        careersPage.openFirstJob();

        jobOverviewPage.verifyJobOverviewElements()
                .checkJobTitleHaveSameTitle(expectedJobName);
    }

    @Test
    @DisplayName("Job application page should contain all required elements")
    void jobApplicationPageShouldContainRequiredElementsTest() {
        careersPage.openFirstJob();

        jobOverviewPage.verifyJobOverviewElements();
    }

    @Test
    @DisplayName("Verify clearing the application form results in reset state")
    void shouldClearResultsInJobApplicationTest() {
        careersPage.openFirstJob();

        jobOverviewPage.openApplicationForm();

        jobApplicationPage.checkClearButtonDisabled()
                .fillPersonalInformationForm()
                .clearFormByClearButton()
                .checkClearButtonDisabled()
                .verifyFormCleared();
    }

    @Test
    @DisplayName("Filter by Search job input should update jobs count")
    void filteringJobsBySearchJobInputShouldUpdateJobsCountTest() {
        var jobsCountBeforeFiltering = careersPage.getStartJobCount();

        careersPage.searchAnyJobWithNonEmptyResults()
                .checkJobResultsCountShouldBeLessThan(jobsCountBeforeFiltering);
    }
}
