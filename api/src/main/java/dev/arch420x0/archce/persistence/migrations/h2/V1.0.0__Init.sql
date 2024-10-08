CREATE
SEQUENCE decision_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE problem_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE role_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE tradeoff_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE usuario_seq START
WITH 1 INCREMENT BY 50;

CREATE TABLE concern
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_concern PRIMARY KEY (id)
);

CREATE TABLE concern_stakeholder
(
    id_concern     BIGINT NOT NULL,
    id_stakeholder BIGINT NOT NULL
);

CREATE TABLE concern_viewpoint
(
    id_concern   BIGINT NOT NULL,
    id_viewpoint BIGINT NOT NULL
);

CREATE TABLE decision
(
    id           BIGINT NOT NULL,
    description  VARCHAR(255),
    solution     VARCHAR(255),
    rationale    VARCHAR(255),
    objective_id BIGINT,
    CONSTRAINT pk_decision PRIMARY KEY (id)
);

CREATE TABLE entity_interest
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    background CLOB,
    purpose CLOB,
    scope CLOB,
    approach CLOB,
    schedule CLOB,
    milestones CLOB,
    CONSTRAINT pk_entityinterest PRIMARY KEY (id)
);

CREATE TABLE metric
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    valor              VARCHAR(255),
    tradeoff_id        BIGINT,
    entity_interest_id BIGINT,
    CONSTRAINT pk_metric PRIMARY KEY (id)
);

CREATE TABLE objective
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    description        VARCHAR(255),
    rationale          VARCHAR(255),
    problem_id         BIGINT,
    entity_interest_id BIGINT,
    CONSTRAINT pk_objective PRIMARY KEY (id)
);

CREATE TABLE problem
(
    id           BIGINT NOT NULL,
    title        VARCHAR(255),
    area         VARCHAR(255),
    aspects      VARCHAR(255),
    risks        VARCHAR(255),
    oportunities VARCHAR(255),
    constraints  VARCHAR(255),
    CONSTRAINT pk_problem PRIMARY KEY (id)
);

CREATE TABLE problem_stakeholder
(
    id_concern     BIGINT NOT NULL,
    id_stakeholder BIGINT NOT NULL
);

CREATE TABLE role
(
    id        BIGINT NOT NULL,
    nome_role VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE stakeholder
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    nome               VARCHAR(255) NOT NULL,
    type               VARCHAR(255),
    entity_interest_id BIGINT,
    CONSTRAINT pk_stakeholder PRIMARY KEY (id)
);

CREATE TABLE tradeoff
(
    id          BIGINT NOT NULL,
    type        VARCHAR(255),
    description VARCHAR(255),
    rationale   VARCHAR(255),
    decision_id BIGINT,
    CONSTRAINT pk_tradeoff PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id    BIGINT NOT NULL,
    login VARCHAR(255),
    senha VARCHAR(255),
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE usuarios_role
(
    rode_id    BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL
);

CREATE TABLE viewpoint
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    overview    VARCHAR(255),
    model       VARCHAR(255),
    conventions VARCHAR(255),
    rationale   VARCHAR(255),
    source      VARCHAR(255),
    CONSTRAINT pk_viewpoint PRIMARY KEY (id)
);

ALTER TABLE tradeoff
    ADD CONSTRAINT DECISION_ID FOREIGN KEY (decision_id) REFERENCES decision (id);

ALTER TABLE metric
    ADD CONSTRAINT FK_METRIC_ON_ENTITYINTEREST FOREIGN KEY (entity_interest_id) REFERENCES entity_interest (id);

ALTER TABLE objective
    ADD CONSTRAINT FK_OBJECTIVE_ON_ENTITYINTEREST FOREIGN KEY (entity_interest_id) REFERENCES entity_interest (id);

ALTER TABLE stakeholder
    ADD CONSTRAINT FK_STAKEHOLDER_ON_ENTITYINTEREST FOREIGN KEY (entity_interest_id) REFERENCES entity_interest (id);

ALTER TABLE decision
    ADD CONSTRAINT OBJECTIVE_ID FOREIGN KEY (objective_id) REFERENCES objective (id);

ALTER TABLE objective
    ADD CONSTRAINT PROBLEM_ID FOREIGN KEY (problem_id) REFERENCES problem (id);

ALTER TABLE metric
    ADD CONSTRAINT TRADEOFF_ID FOREIGN KEY (tradeoff_id) REFERENCES tradeoff (id);

ALTER TABLE concern_stakeholder
    ADD CONSTRAINT fk_consta_on_concern FOREIGN KEY (id_stakeholder) REFERENCES concern (id);

ALTER TABLE concern_stakeholder
    ADD CONSTRAINT fk_consta_on_stakeholder FOREIGN KEY (id_concern) REFERENCES stakeholder (id);

ALTER TABLE concern_viewpoint
    ADD CONSTRAINT fk_convie_on_concern FOREIGN KEY (id_viewpoint) REFERENCES concern (id);

ALTER TABLE concern_viewpoint
    ADD CONSTRAINT fk_convie_on_viewpoint FOREIGN KEY (id_concern) REFERENCES viewpoint (id);

ALTER TABLE problem_stakeholder
    ADD CONSTRAINT fk_prosta_on_problem FOREIGN KEY (id_stakeholder) REFERENCES problem (id);

ALTER TABLE problem_stakeholder
    ADD CONSTRAINT fk_prosta_on_stakeholder FOREIGN KEY (id_concern) REFERENCES stakeholder (id);

ALTER TABLE usuarios_role
    ADD CONSTRAINT fk_usurol_on_role FOREIGN KEY (rode_id) REFERENCES role (id);

ALTER TABLE usuarios_role
    ADD CONSTRAINT fk_usurol_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);