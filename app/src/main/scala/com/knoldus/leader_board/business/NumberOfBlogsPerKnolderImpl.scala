package com.knoldus.leader_board.business

import com.knoldus.leader_board.infrastructure.{ReadAllTime, ReadBlog}
import com.knoldus.leader_board.{BlogCount, KnolderBlogCount}
import com.typesafe.scalalogging._

class NumberOfBlogsPerKnolderImpl(readBlog: ReadBlog, readAllTime: ReadAllTime)
  extends NumberOfBlogsPerKnolder with LazyLogging {

  /**
   * Gets knolder id of knolders along with blog count from all time table.
   *
   * @return List of blog count of knolders along with their knolder id.
   */
  override def getKnolderBlogCount: List[KnolderBlogCount] = {
    val numberOfBlogsOfKnolders: List[BlogCount] = readBlog.fetchKnoldersWithBlogs
    logger.info("Fetching knolder id of knolders from all time table.")
    numberOfBlogsOfKnolders.map { numberOfBlogsOfKnolder =>
      KnolderBlogCount(readAllTime.fetchKnolderIdFromAllTime(numberOfBlogsOfKnolder.knolderId),
        numberOfBlogsOfKnolder)
    }
  }
}
