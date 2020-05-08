package com.knoldus.leader_board.infrastructure

import org.scalatest.Suites

class MainSpec extends Suites(new ReadKnolderImplSpec, new ReadBlogImplSpec,
  new WriteAllTimeImplSpec, new ReadAllTimeImplSpec,
  new WriteAllTimeReputationImplSpec, new ReadAllTimeReputationImplSpec)
