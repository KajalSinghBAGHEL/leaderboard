CREATE TABLE IF NOT EXISTS knolder(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
full_name VARCHAR(100) NOT NULL,
wordpress_id VARCHAR(100) UNIQUE,
email_id VARCHAR(100) UNIQUE,
active_status BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS blog(
id INT PRIMARY KEY NOT NULL,
wordpress_id VARCHAR(100) NOT NULL,
published_on TIMESTAMP NOT NULL,
title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS knolx(
id VARCHAR(255) PRIMARY KEY NOT NULL,
email_id VARCHAR(100) NOT NULL,
delivered_on TIMESTAMP NOT NULL,
title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS all_time_reputation(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
knolder_id INT NOT NULL,
score INT NOT NULL,
rank INT NOT NULL
);

CREATE TABLE IF NOT EXISTS monthly_reputation(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
knolder_id INT NOT NULL,
score INT NOT NULL,
rank INT NOT NULL
);

CREATE TABLE IF NOT EXISTS quarterly_reputation(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
knolder_id INT NOT NULL,
streak VARCHAR(20) NOT NULL
);
