CREATE TABLE SimpleCategory (id INTEGER NOT NULL, childIndex INTEGER, depth INTEGER, description VARCHAR(100), longDescription VARCHAR(1024), name VARCHAR(255), parentCategoryId INTEGER, PRIMARY KEY (id)) TYPE = innodb;
CREATE INDEX I_SMPLGRY_PARENTCATEGORY ON SimpleCategory (parentCategoryId);
