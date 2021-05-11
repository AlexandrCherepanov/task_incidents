package com.cherepanov.task_incidents

import java.io.Serializable

class ModelIncident (
    val EXTSYSNAME: String,
    val DESCRIPTION: String,
    val TICKETID: String,
    val ISKNOWNERRORDATE: String,
    val TARGETFINISH: String,
    val STATUS: String,
    val REPORTEDBY: String,
    val CLASSIDMAIN: String,
    val CRITIC_LEVEL: String,
    val NORM: String,
    val LNORM: String
): Serializable