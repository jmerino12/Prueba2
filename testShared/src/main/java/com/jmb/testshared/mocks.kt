package com.jmb.testshared

import com.jmb.domain.Post
import com.jmb.domain.User

val mockedUser = User(
    address = null,
    company = null,
    email = "email",
    id = 0,
    name = "name",
    phone = "phone",
    username = "username",
    website = "website"
)

val mockedPost = Post(
    body = "body",
    id = 1,
    title = "title",
    userId = 1
)