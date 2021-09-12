package com.jmb.prueba.model

import com.jmb.domain.User
import com.jmb.domain.Address
import com.jmb.domain.Geo
import com.jmb.domain.Company
import com.jmb.prueba.model.database.User as DomainUser
import com.jmb.prueba.model.server.User as ServerUser
import com.jmb.prueba.model.server.Address as ServerAddress
import com.jmb.prueba.model.server.Geo as ServerGeo
import com.jmb.prueba.model.server.Company as ServerCompany


fun User.toRoomMovie(): DomainUser = DomainUser(
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
    website = website
)

fun DomainUser.toDomainUser(): User = User(
    address = address,
    company = company,
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
    website = website
)

fun ServerUser.toDomainMovie(): User =
    User(
        address = address?.toDomainAddress(),
        company = company?.toDomainCompany(),
        email = email,
        id = id,
        name = name,
        phone = phone,
        username = username,
        website = website
    )

fun ServerAddress.toDomainAddress(): Address = Address(
    geo = geo.toDomainGeo(),
    city = city,
    street = street,
    suite = suite,
    zipcode = zipcode
)

fun ServerGeo.toDomainGeo(): Geo = Geo(
    lat = lat,
    lng = lng
)

fun ServerCompany.toDomainCompany(): Company = Company(
    bs = bs,
    catchPhrase = catchPhrase,
    name = name
)