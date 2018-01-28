create table shows
(
   id integer not null,
   name varchar(255) not null,
   primary key(id)
);


create table episodes
(
    id integer not null,
    showId integer not null,
    absoluteNumber integer,
    airedEpisodeNumber integer,
    airedSeason integer,
    dvdEpisodeNumber integer,
    dvdSeason integer,
    episodeName varchar(255),
    firstAired date,
    lastUpdated integer,
    overview varchar(255),
    primary key(id)
);