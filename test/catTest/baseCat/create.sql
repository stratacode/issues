CREATE TABLE SimpleCategory (id INTEGER NOT NULL, childIndex INTEGER, depth INTEGER, description VARCHAR(100), longDescription VARCHAR(1024), name VARCHAR(255), parentCategoryId INTEGER, PRIMARY KEY (id)) TYPE = innodb;
CREATE INDEX I_SMPLGRY_PARENTCATEGORY ON SimpleCategory (parentCategoryId);
CREATE TABLE SimpleCategory_SimpleCategory (fixedChildCategories_id INTEGER NOT NULL, parentCategories_id INTEGER NOT NULL) TYPE = innodb;
CREATE INDEX I2 ON SimpleCategory_SimpleCategory (fixedChildCategories_id);
CREATE INDEX I3 ON SimpleCategory_SimpleCategory (parentCategories_id);
