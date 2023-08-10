USE bank;
CREATE TABLE IF NOT EXISTS ACCOUNT(
	AccountNumber INT NOT NULL PRIMARY KEY,
    AccountBalance DOUBLE NOT NULL,
    userID BIGINT NOT NULL,
    AccountType VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS `User_Details`(
    `userID` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `FirstName` VARCHAR(255) NOT NULL,
    `SecondName` VARCHAR(255) NOT NULL,
    `CurrentAddress` VARCHAR(255) NOT NULL,
    `Gender` VARCHAR(255) NOT NULL,
    `DOB` DATE NOT NULL,
    `FatherName` VARCHAR(255) NOT NULL,
    `PermanentAddress` VARCHAR(255) NOT NULL,
    `Username` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `isAdmin` VARCHAR(255) NOT NULL,
    `PhoneNumber` BIGINT NOT NULL,
    `Aadhar` BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Payee`(
    `payee_id` BIGINT UNSIGNED NOT NULL  PRIMARY KEY,
    `userID` BIGINT NOT NULL,
    `FirstName` VARCHAR(255) NOT NULL,
    `LastName` VARCHAR(255) NOT NULL,
    `NickName` VARCHAR(255) NOT NULL,
    `AccountNumber` BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Transactions`(
    `referenceID` BIGINT UNSIGNED NOT NULL  PRIMARY KEY,
    `userID` BIGINT NOT NULL,
    `Amount` BIGINT NOT NULL,
    `Payee` VARCHAR(255) NOT NULL,
    `Time` DATETIME NOT NULL,
    `Remarks` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Account` ADD FOREIGN KEY(`userID`) REFERENCES `User_Details`(`userID`);
ALTER TABLE
    `Transactions` ADD CONSTRAINT `transactions_userid_foreign` FOREIGN KEY(`userID`) REFERENCES `Account`(`userID`);
ALTER TABLE
    `Account` ADD CONSTRAINT `account_userid_foreign` FOREIGN KEY(`userID`) REFERENCES `Payee`(`userID`);