package com.knoldus.leader_board.infrastructure

import java.sql.{Connection, PreparedStatement, Timestamp}

import com.knoldus.leader_board.{Contribution, ContributionDetails, DatabaseConnection, KnolderDetails}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterEach, DoNotDiscover}

@DoNotDiscover
class FetchKnolderContributionDetailsImplSpec extends DBSpec with BeforeAndAfterEach {
  implicit val connection: Connection = DatabaseConnection.connection(ConfigFactory.load())
  val fetchKnolderDetails: FetchKnolderContributionDetails = new FetchKnolderContributionDetailsImpl(ConfigFactory.load())

  override def afterEach(): Unit = {
    cleanUpDatabase(connection)
  }

  override def beforeEach(): Unit = {
    createTable(connection)
  }

  "fetch knolder details" should {
    val date = Timestamp.valueOf("2020-04-13 13:10:40")

    def insertMonthlyContribution: Unit = {
      val insertMonthlyContribution: String =
        """
          |insert into monthlycontribution(id, knolder_id, blog_score, knolx_score, webinar_score, techhub_score
          |, oscontribution_score, book_score, conference_score, researchpaper_score, meetup_score, month, year)
          |values (?,?,?,?,?,?,?,?,?,?,?,?,?)
""".stripMargin

      val preparedStmt: PreparedStatement = connection.prepareStatement(insertMonthlyContribution)
      preparedStmt.setInt(1, 1)
      preparedStmt.setInt(2, 1)
      preparedStmt.setInt(3, 10)
      preparedStmt.setInt(4, 40)
      preparedStmt.setInt(5, 30)
      preparedStmt.setInt(6, 30)
      preparedStmt.setInt(7, 60)
      preparedStmt.setInt(8, 200)
      preparedStmt.setInt(9, 200)
      preparedStmt.setInt(10, 100)
      preparedStmt.setInt(11, 60)
      preparedStmt.setString(12, "APRIL")
      preparedStmt.setInt(13, 2020)
      preparedStmt.execute
      preparedStmt.close()
    }

    def insertAllTimeReputation(): Unit = {
      val insertAllTimeReputationData: String =
        """
          |insert into all_time_reputation(id, knolder_id, score, rank)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt: PreparedStatement = connection.prepareStatement(insertAllTimeReputationData)
      preparedStmt.setInt(1, 1)
      preparedStmt.setInt(2, 1)
      preparedStmt.setInt(3, 730)
      preparedStmt.setInt(4, 1)
      preparedStmt.execute
      preparedStmt.close()
    }

    def insertBlog(): Unit = {
      val insertBlogOne: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtOne: PreparedStatement = connection.prepareStatement(insertBlogOne)
      preparedStmtOne.setInt(1, 1001)
      preparedStmtOne.setString(2, "mukesh01")
      preparedStmtOne.setTimestamp(3, date)
      preparedStmtOne.setString(4, "windows handling using selenium webdriver")
      preparedStmtOne.execute
      preparedStmtOne.close()

      val insertBlogTwo: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtTwo: PreparedStatement = connection.prepareStatement(insertBlogTwo)
      preparedStmtTwo.setInt(1, 1004)
      preparedStmtTwo.setString(2, "mukesh01")
      preparedStmtTwo.setTimestamp(3, date)
      preparedStmtTwo.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmtTwo.execute
      preparedStmtTwo.close()
    }

    def insertKnolx() {
      val insertKnolxOne: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtThree: PreparedStatement = connection.prepareStatement(insertKnolxOne)
      preparedStmtThree.setInt(1, 1)
      preparedStmtThree.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtThree.setTimestamp(3, date)
      preparedStmtThree.setString(4, "Reactive Microservices")
      preparedStmtThree.execute
      preparedStmtThree.close()

      val insertKnolxTwo: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtFour: PreparedStatement = connection.prepareStatement(insertKnolxTwo)
      preparedStmtFour.setInt(1, 4)
      preparedStmtFour.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtFour.setTimestamp(3, date)
      preparedStmtFour.setString(4, "Delta Lake")
      preparedStmtFour.execute
      preparedStmtFour.close()
    }

    def insertWebinar() {
      val insertWebinarOne: String =
        """
          |insert into webinar(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin


      val preparedStmtFive: PreparedStatement = connection.prepareStatement(insertWebinarOne)
      preparedStmtFive.setInt(1, 1)
      preparedStmtFive.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtFive.setTimestamp(3, date)
      preparedStmtFive.setString(4, "Reactive Microservices")
      preparedStmtFive.execute
      preparedStmtFive.close()

      val insertWebinarTwo: String =
        """
          |insert into webinar(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtSix: PreparedStatement = connection.prepareStatement(insertWebinarTwo)
      preparedStmtSix.setInt(1, 4)
      preparedStmtSix.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtSix.setTimestamp(3, date)
      preparedStmtSix.setString(4, "Delta Lake")
      preparedStmtSix.execute
      preparedStmtSix.close()
    }

    def insertKnolder() {
      val insertKnolder: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmtSeven: PreparedStatement = connection.prepareStatement(insertKnolder)
      preparedStmtSeven.setInt(1, 1)
      preparedStmtSeven.setString(2, "Mukesh Gupta")
      preparedStmtSeven.setString(3, "mukesh01")
      preparedStmtSeven.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmtSeven.setBoolean(5, true)
      preparedStmtSeven.execute
      preparedStmtSeven.close()
    }

    def insertTechHub() {
      val insertTechhubOne: String =
        """
          |insert into techhub(id, email_id, uploaded_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtEight: PreparedStatement = connection.prepareStatement(insertTechhubOne)
      preparedStmtEight.setInt(1, 1)
      preparedStmtEight.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtEight.setTimestamp(3, date)
      preparedStmtEight.setString(4, "Reactive Microservices")
      preparedStmtEight.execute
      preparedStmtEight.close()

      val insertTechhubTwo: String =
        """
          |insert into techhub(id, email_id, uploaded_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtNine: PreparedStatement = connection.prepareStatement(insertTechhubTwo)
      preparedStmtNine.setInt(1, 4)
      preparedStmtNine.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtNine.setTimestamp(3, date)
      preparedStmtNine.setString(4, "Delta Lake")
      preparedStmtNine.execute
      preparedStmtNine.close()
    }

    def insertOSContribution(): Unit = {
      val insertOsContributionOne: String =
        """
          |insert into oscontribution(id, email_id, contributed_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtOne: PreparedStatement = connection.prepareStatement(insertOsContributionOne)
      preparedStmtOne.setInt(1, 1)
      preparedStmtOne.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtOne.setTimestamp(3, date)
      preparedStmtOne.setString(4, "Reactive Microservices")
      preparedStmtOne.execute
      preparedStmtOne.close()

      val insertOsContributionTwo: String =
        """
          |insert into oscontribution(id, email_id, contributed_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtTwo: PreparedStatement = connection.prepareStatement(insertOsContributionTwo)
      preparedStmtTwo.setInt(1, 4)
      preparedStmtTwo.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtTwo.setTimestamp(3, date)
      preparedStmtTwo.setString(4, "Delta Lake")
      preparedStmtTwo.execute
      preparedStmtTwo.close()
    }


    def insertConferenceContribution(): Unit = {
      val insertConferenceOne: String =
        """
          |insert into conference(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtOne: PreparedStatement = connection.prepareStatement(insertConferenceOne)
      preparedStmtOne.setInt(1, 1)
      preparedStmtOne.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtOne.setTimestamp(3, date)
      preparedStmtOne.setString(4, "Reactive Microservices")
      preparedStmtOne.execute
      preparedStmtOne.close()

      val insertConferenceTwo: String =
        """
          |insert into conference(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtTwo: PreparedStatement = connection.prepareStatement(insertConferenceTwo)
      preparedStmtTwo.setInt(1, 4)
      preparedStmtTwo.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtTwo.setTimestamp(3, date)
      preparedStmtTwo.setString(4, "Delta Lake")
      preparedStmtTwo.execute
      preparedStmtTwo.close()
    }

    def insertBooksContribution(): Unit = {
      val insertBookOne: String =
        """
          |insert into book(id, email_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtOne: PreparedStatement = connection.prepareStatement(insertBookOne)
      preparedStmtOne.setInt(1, 1)
      preparedStmtOne.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtOne.setTimestamp(3, date)
      preparedStmtOne.setString(4, "Reactive Microservices")
      preparedStmtOne.execute
      preparedStmtOne.close()

      val insertBookTwo: String =
        """
          |insert into book(id, email_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtTwo: PreparedStatement = connection.prepareStatement(insertBookTwo)
      preparedStmtTwo.setInt(1, 4)
      preparedStmtTwo.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtTwo.setTimestamp(3, date)
      preparedStmtTwo.setString(4, "Delta Lake")
      preparedStmtTwo.execute
      preparedStmtTwo.close()
    }

    def insertResearchPaperContribution(): Unit = {
      val insertResearchPaperOne: String =
        """
          |insert into researchpaper(id, email_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtOne: PreparedStatement = connection.prepareStatement(insertResearchPaperOne)
      preparedStmtOne.setInt(1, 1)
      preparedStmtOne.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtOne.setTimestamp(3, date)
      preparedStmtOne.setString(4, "Reactive Microservices")
      preparedStmtOne.execute
      preparedStmtOne.close()

      val insertResearchPaperTwo: String =
        """
          |insert into researchpaper(id, email_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtTwo: PreparedStatement = connection.prepareStatement(insertResearchPaperTwo)
      preparedStmtTwo.setInt(1, 4)
      preparedStmtTwo.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtTwo.setTimestamp(3, date)
      preparedStmtTwo.setString(4, "Delta Lake")
      preparedStmtTwo.execute
      preparedStmtTwo.close()
    }

    def insertMeetup() {
      val insertMeetupOne: String =
        """
          |insert into meetup(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin


      val preparedStmtFive: PreparedStatement = connection.prepareStatement(insertMeetupOne)
      preparedStmtFive.setInt(1, 1)
      preparedStmtFive.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtFive.setTimestamp(3, date)
      preparedStmtFive.setString(4, "Reactive Microservices")
      preparedStmtFive.execute
      preparedStmtFive.close()

      val insertMeetupTwo: String =
        """
          |insert into meetup(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmtSix: PreparedStatement = connection.prepareStatement(insertMeetupTwo)
      preparedStmtSix.setInt(1, 4)
      preparedStmtSix.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmtSix.setTimestamp(3, date)
      preparedStmtSix.setString(4, "Delta Lake")
      preparedStmtSix.execute
      preparedStmtSix.close()
    }


    "return monthly details of specific knolder" in {
      insertBlog()
      insertKnolx()
      insertWebinar()
      insertTechHub()
      insertKnolder()
      insertOSContribution()
      insertConferenceContribution()
      insertBooksContribution()
      insertResearchPaperContribution()
      insertMeetup()
      insertMonthlyContribution

      val bookTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val bookDetails = Contribution("Books", 2, 200, bookTitles)

      val reseachPaperTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val researchPaperDetails = Contribution("Research Paper", 2, 100, reseachPaperTitles)

      val osContributionTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val osContributionDetails = Contribution("OS Contribution", 2, 60, osContributionTitles)

      val blogTitles = List(ContributionDetails("windows handling using selenium webdriver", date.toString),
        ContributionDetails("Java 9: Enhance your Jav…ptional API enhancement", date.toString))
      val blogDetails = Contribution("Blogs", 2, 10, blogTitles)

      val knolxTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val knolxDetails = Contribution("Knolx", 2, 40, knolxTitles)

      val webinarTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val webinarDetails = Contribution("Webinar", 2, 30, webinarTitles)

      val techhubTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val techhubDetails = Contribution("TechHub", 2, 30, techhubTitles)

      val conferenceTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val conferenceDetails = Contribution("Conferences", 2, 200, conferenceTitles)

      val meetupTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val meetupDetails = Contribution("Meetup", 2, 60, meetupTitles)

      val contributions = List(blogDetails, knolxDetails, webinarDetails, techhubDetails, osContributionDetails, conferenceDetails,
        bookDetails, researchPaperDetails, meetupDetails)

      val knolderDetails = KnolderDetails("Mukesh Gupta", 730, contributions)

      fetchKnolderDetails.fetchKnolderMonthlyDetails(1, 4, 2020).
        map(details => assert(details == knolderDetails))
    }

    "return monthly details of specific knolder when there in no details in monthly contribution table" in {

      insertKnolder()

      val bookTitles = List.empty
      val bookDetails = Contribution("Books", 0, 0, bookTitles)

      val reseachPaperTitles = List.empty
      val researchPaperDetails = Contribution("Research Paper", 0, 0, reseachPaperTitles)

      val conferenceTitles = List.empty
      val conferenceDetails = Contribution("Conferences", 0, 0, conferenceTitles)

      val osContributionTitles = List.empty
      val osContributionDetails = Contribution("OS Contribution", 0, 0, osContributionTitles)

      val techhubTitles = List.empty
      val techhubDetails = Contribution("TechHub", 0, 0, techhubTitles)

      val blogTitles = List.empty
      val blogDetails = Contribution("Blogs", 0, 0, blogTitles)

      val knolxTitles = List.empty
      val knolxDetails = Contribution("Knolx", 0, 0, knolxTitles)

      val webinarTitles = List.empty
      val webinarDetails = Contribution("Webinar", 0, 0, webinarTitles)

      val meetupTitles = List.empty
      val meetupDetails = Contribution("Meetup", 0, 0, meetupTitles)

      val contributions = List(blogDetails, knolxDetails, webinarDetails, techhubDetails,
        osContributionDetails, conferenceDetails, bookDetails, researchPaperDetails, meetupDetails)

      val knolderDetails = KnolderDetails("Mukesh Gupta", 0, contributions)

      fetchKnolderDetails.fetchKnolderMonthlyDetails(1, 6, 2020).
        map(details => assert(details == knolderDetails))
    }

    "return all time details of specific knolder" in {
      insertAllTimeReputation()
      insertBlog()
      insertKnolx()
      insertWebinar()
      insertTechHub()
      insertKnolder()
      insertOSContribution()
      insertConferenceContribution()
      insertBooksContribution()
      insertResearchPaperContribution()
      insertMeetup()
      insertMonthlyContribution


      val bookTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val bookDetails = Contribution("Books", 2, 200, bookTitles)

      val reseachPaperTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val researchPaperDetails = Contribution("Research Paper", 2, 100, reseachPaperTitles)

      val conferenceTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val conferenceDetails = Contribution("Conferences", 2, 200, conferenceTitles)

      val osContributionTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val osContributionDetails = Contribution("OS Contribution", 2, 60, osContributionTitles)
      val techhubTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val techhubDetails = Contribution("TechHub", 2, 30, techhubTitles)


      val blogTitles = List(ContributionDetails("windows handling using selenium webdriver", date.toString),
        ContributionDetails("Java 9: Enhance your Jav…ptional API enhancement", date.toString))
      val blogDetails = Contribution("Blogs", 2, 10, blogTitles)

      val knolxTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val knolxDetails = Contribution("Knolx", 2, 40, knolxTitles)

      val webinarTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val webinarDetails = Contribution("Webinar", 2, 30, webinarTitles)

      val meetupTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val meetupDetails = Contribution("Meetup", 2, 60, meetupTitles)

      val contributions = List(blogDetails, knolxDetails, webinarDetails, techhubDetails,
        osContributionDetails, conferenceDetails, bookDetails, researchPaperDetails, meetupDetails)
      val knolderDetails = KnolderDetails("Mukesh Gupta", 730, contributions)

      fetchKnolderDetails.fetchKnolderAllTimeDetails(1).
        map(details => assert(details == knolderDetails))
    }

    "return all time details of specific knolder when details are not in monthly contribution table" in {
      insertAllTimeReputation()
      insertKnolder()

      val bookTitles = List.empty
      val bookDetails = Contribution("Books", 0, 0, bookTitles)

      val reseachPaperTitles = List.empty
      val researchPaperDetails = Contribution("Research Paper", 0, 0, reseachPaperTitles)

      val conferenceTitles = List.empty
      val conferenceDetails = Contribution("Conferences", 0, 0, conferenceTitles)

      val osContributionTitles = List.empty
      val osContributionDetails = Contribution("OS Contribution", 0, 0, osContributionTitles)

      val techhubTitles = List.empty
      val techhubDetails = Contribution("TechHub", 0, 0, techhubTitles)

      val blogTitles = List.empty
      val blogDetails = Contribution("Blogs", 0, 0, blogTitles)

      val knolxTitles = List.empty
      val knolxDetails = Contribution("Knolx", 0, 0, knolxTitles)

      val webinarTitles = List.empty
      val webinarDetails = Contribution("Webinar", 0, 0, webinarTitles)

      val meetupTitles = List.empty
      val meetupDetails = Contribution("Meetup", 0, 0, meetupTitles)

      val contributions = List(blogDetails, knolxDetails, webinarDetails, techhubDetails,
        osContributionDetails, conferenceDetails, bookDetails, researchPaperDetails, meetupDetails)
      val knolderDetails = KnolderDetails("Mukesh Gupta", 730, contributions)

      fetchKnolderDetails.fetchKnolderAllTimeDetails(1).
        map(details => assert(details == knolderDetails))
    }
    "return monthly details of blogs of knolder" in {

      insertBlog()
      insertKnolder()
      insertMonthlyContribution

      val blogTitles = List(ContributionDetails("windows handling using selenium webdriver", date.toString),
        ContributionDetails("Java 9: Enhance your Jav…ptional API enhancement", date.toString))
      val blogDetails = Contribution("Blogs", 2, 10, blogTitles)

      assert(fetchKnolderDetails.fetchKnolderMonthlyBlogDetails(4, 2020, 1) == blogDetails)

    }
    "return monthly details of knolx of knolder" in {


      insertKnolx()
      insertKnolder()
      insertMonthlyContribution


      val knolxTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val knolxDetails = Contribution("Knolx", 2, 40, knolxTitles)

      assert(fetchKnolderDetails.fetchKnolderMonthlyKnolxDetails(4, 2020, 1) == knolxDetails)

    }

    "return monthly details of techhub of knolder" in {

      insertTechHub()
      insertKnolder()
      insertMonthlyContribution

      val techhubTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val techhubDetails = Contribution("TechHub", 2, 30, techhubTitles)

      assert(fetchKnolderDetails.fetchKnolderMonthlyTechHubDetails(4, 2020, 1) == techhubDetails)
    }

    "return monthly details of webinar of knolder" in {

      insertWebinar()
      insertKnolder()
      insertMonthlyContribution

      val webinarTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val webinarDetails = Contribution("Webinar", 2, 30, webinarTitles)

      assert(fetchKnolderDetails.fetchKnolderMonthlyWebinarDetails(4, 2020, 1) == webinarDetails)
    }

    "return all time details of webinar of knolder" in {
      insertWebinar()
      insertKnolder()
      insertMonthlyContribution

      val webinarTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val webinarDetails = Contribution("Webinar", 2, 30, webinarTitles)

      assert(fetchKnolderDetails.fetchAllTimeWebinarDetails(1) == webinarDetails)

    }
    "return all time details of knolx of knolder" in {

      insertKnolx()
      insertKnolder()
      insertMonthlyContribution

      val knolxTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val knolxDetails = Contribution("Knolx", 2, 40, knolxTitles)

      assert(fetchKnolderDetails.fetchAllTimeknolxDetails(1) == knolxDetails)
    }
    "return all time details of blogs of knolder" in {
      insertBlog()
      insertKnolder()
      insertMonthlyContribution

      val blogTitles = List(ContributionDetails("windows handling using selenium webdriver", date.toString),
        ContributionDetails("Java 9: Enhance your Jav…ptional API enhancement", date.toString))
      val blogDetails = Contribution("Blogs", 2, 10, blogTitles)

      assert(fetchKnolderDetails.fetchAllTimeBlogDetails(1) == blogDetails)

    }
    "return all time details of techhub of knolder" in {

      insertTechHub()
      insertKnolder()
      insertMonthlyContribution


      val techhubTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val techhubDetails = Contribution("TechHub", 2, 30, techhubTitles)

      assert(fetchKnolderDetails.fetchAllTimeTechHubDetails(1) == techhubDetails)
    }
    "return monthly details of oscontribution of knolder" in {


      insertKnolder()
      insertOSContribution()
      insertMonthlyContribution

      val osContributionTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val osContributionDetails = Contribution("OS Contribution", 2, 60, osContributionTitles)


      assert(fetchKnolderDetails.fetchKnolderMonthlyOsContributionDetails(4, 2020, 1) == osContributionDetails)

    }
    "return all time details of oscontribution of knolder" in {

      insertKnolder()
      insertOSContribution()
      insertMonthlyContribution

      val osContributionTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val osContributionDetails = Contribution("OS Contribution", 2, 60, osContributionTitles)

      assert(fetchKnolderDetails.fetchAllTimeOsContributionDetails(1) == osContributionDetails)
    }

    "return monthly details of conferences of knolder" in {


      insertKnolder()
      insertConferenceContribution()
      insertMonthlyContribution

      val conferenceTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val conferenceDetails = Contribution("Conferences", 2, 200, conferenceTitles)


      assert(fetchKnolderDetails.fetchKnolderMonthlyConferenceDetails(4, 2020, 1) == conferenceDetails)

    }
    "return all time details of conference of knolder" in {

      insertKnolder()
      insertConferenceContribution()
      insertMonthlyContribution

      val conferenceTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val conferenceDetails = Contribution("Conferences", 2, 200, conferenceTitles)

      assert(fetchKnolderDetails.fetchAllTimeConferenceDetails(1) == conferenceDetails)
    }

    "return monthly details of books of knolder" in {


      insertKnolder()
      insertBooksContribution()
      insertMonthlyContribution

      val bookTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val bookDetails = Contribution("Books", 2, 200, bookTitles)


      assert(fetchKnolderDetails.fetchKnolderMonthlyBookDetails(4, 2020, 1) == bookDetails)

    }
    "return all time details of books of knolder" in {

      insertKnolder()
      insertBooksContribution()
      insertMonthlyContribution

      val bookTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val bookDetails = Contribution("Books", 2, 200, bookTitles)

      assert(fetchKnolderDetails.fetchAllTimeBookDetails(1) == bookDetails)
    }


    "return monthly details of research paper of knolder" in {


      insertKnolder()
      insertResearchPaperContribution()
      insertMonthlyContribution

      val researchPaperTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val researchPaperDetails = Contribution("Research Paper", 2, 100, researchPaperTitles)


      assert(fetchKnolderDetails.fetchKnolderMonthlyResearchPaperDetails(4, 2020, 1) == researchPaperDetails)

    }
    "return all time details of research paper of knolder" in {

      insertKnolder()
      insertResearchPaperContribution()
      insertMonthlyContribution
      val researchPaperTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))

      val researchPaperDetails = Contribution("Research Paper", 2, 100, researchPaperTitles)

      assert(fetchKnolderDetails.fetchAllTimeResearchPaperDetails(1) == researchPaperDetails)
    }


    "return monthly details of meetup of knolder" in {

      insertMeetup()
      insertKnolder()
      insertMonthlyContribution


      val meetupTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val meetupDetails = Contribution("Meetup", 2, 60, meetupTitles)

      assert(fetchKnolderDetails.fetchKnolderMonthlyMeetupDetails(4, 2020, 1) == meetupDetails)
    }

    "return all time details of meetup of knolder" in {
      insertMeetup()
      insertKnolder()
      insertMonthlyContribution

      val meetupTitles = List(ContributionDetails("Reactive Microservices", date.toString),
        ContributionDetails("Delta Lake", date.toString))
      val meetupDetails = Contribution("Meetup", 2, 60, meetupTitles)

      assert(fetchKnolderDetails.fetchAllTimeMeetupDetails(1) == meetupDetails)

    }
  }
}
