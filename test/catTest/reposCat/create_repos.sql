CREATE TABLE SimpleCategory (id INTEGER NOT NULL, childIndex INTEGER, depth INTEGER, description VARCHAR(100), longDescription VARCHAR(1024), name VARCHAR(255), parentCategoryId INTEGER, PRIMARY KEY (id)) TYPE = innodb;
CREATE INDEX I_SMPLGRY_PARENTCATEGORY ON SimpleCategory (parentCategoryId);
CREATE TABLE SimpleCategory_SimpleCategory (fixedChildCategories_id INTEGER NOT NULL, parentCategories_id INTEGER NOT NULL, sequenceNum INTEGER) TYPE = innodb;
CREATE INDEX I2 ON SimpleCategory_SimpleCategory (fixedChildCategories_id);
CREATE INDEX I3 ON SimpleCategory_SimpleCategory (parentCategories_id);



create table das_id_generator (
        id_space_name   varchar(60)     not null,
        seed    bigint  not null,
        batch_size      integer not null,
        prefix  varchar(10)     null,
        suffix  varchar(10)     null
,constraint das_id_generator_p primary key (id_space_name));

