create table staff_diet
(
    id              bigint auto_increment primary key,
    start_date date                                null,
    end_date   date                                null,
    type            enum ('DINNER', 'LUNCH', 'MORNING') null,
    monday_menu     varchar(255)                        not null default '',
    tuesday_menu    varchar(255)                        not null default '',
    wednesday_menu  varchar(255)                        not null default '',
    thursday_menu   varchar(255)                        not null default '',
    friday_menu     varchar(255)                        not null default '',
    is_deleted      bit                                 not null default false,
    updated_at      datetime(6)                         not null,
    created_at      datetime(6)                         not null
);

create table student_diet
(
    id              bigint auto_increment primary key,
    start_date date                                null,
    end_date   date                                null,
    type            enum ('DINNER', 'LUNCH', 'MORNING') null,
    company_name    varchar(255)                        not null,
    monday_menu     varchar(255)                        not null default '',
    tuesday_menu    varchar(255)                        not null default '',
    wednesday_menu  varchar(255)                        not null default '',
    thursday_menu   varchar(255)                        not null default '',
    friday_menu     varchar(255)                        not null default '',
    is_deleted      bit                                 not null default false,
    updated_at      datetime(6)                         not null,
    created_at      datetime(6)                         not null
);