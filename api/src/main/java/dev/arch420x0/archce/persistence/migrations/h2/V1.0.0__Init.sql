CREATE TABLE concern
(
    id            BIGINT       NOT NULL,
    `description` VARCHAR(255) NULL,
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
    id            BIGINT       NOT NULL,
    `description` VARCHAR(255) NULL,
    solution      VARCHAR(255) NULL,
    rationale     VARCHAR(255) NULL,
    objective_id  BIGINT       NULL,
    CONSTRAINT pk_decision PRIMARY KEY (id)
);

CREATE TABLE entity_interest
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NULL,
    background VARCHAR(255)          NULL,
    purpose    VARCHAR(255)          NULL,
    scope      VARCHAR(255)          NULL,
    approach   VARCHAR(255)          NULL,
    schedule   VARCHAR(255)          NULL,
    milestones VARCHAR(255)          NULL,
    CONSTRAINT pk_entityinterest PRIMARY KEY (id)
);

CREATE TABLE metric
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    valor              VARCHAR(255)          NULL,
    tradeoff_id        BIGINT                NULL,
    entity_interest_id BIGINT                NULL,
    CONSTRAINT pk_metric PRIMARY KEY (id)
);

CREATE TABLE objective
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    `description`      VARCHAR(255)          NULL,
    rationale          VARCHAR(255)          NULL,
    problem_id         BIGINT                NULL,
    entity_interest_id BIGINT                NULL,
    CONSTRAINT pk_objective PRIMARY KEY (id)
);

CREATE TABLE problem
(
    id           BIGINT       NOT NULL,
    title        VARCHAR(255) NULL,
    area         VARCHAR(255) NULL,
    aspects      VARCHAR(255) NULL,
    risks        VARCHAR(255) NULL,
    oportunities VARCHAR(255) NULL,
    constraints  VARCHAR(255) NULL,
    CONSTRAINT pk_problem PRIMARY KEY (id)
);

CREATE TABLE problem_stakeholder
(
    id_concern     BIGINT NOT NULL,
    id_stakeholder BIGINT NOT NULL
);

CREATE TABLE `role`
(
    id        BIGINT       NOT NULL,
    nome_role VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE stakeholder
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(255)          NOT NULL,
    type               VARCHAR(255)          NULL,
    entity_interest_id BIGINT                NULL,
    CONSTRAINT pk_stakeholder PRIMARY KEY (id)
);

CREATE TABLE tradeoff
(
    id            BIGINT       NOT NULL,
    type          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    rationale     VARCHAR(255) NULL,
    decision_id   BIGINT       NULL,
    CONSTRAINT pk_tradeoff PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id    BIGINT       NOT NULL,
    login VARCHAR(255) NULL,
    senha VARCHAR(255) NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE usuarios_role
(
    rode_id    BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL
);

CREATE TABLE viewpoint
(
    id          BIGINT       NOT NULL,
    title       VARCHAR(255) NULL,
    rationale   VARCHAR(255) NULL,
    model       VARCHAR(255) NULL,
    conventions VARCHAR(255) NULL,
    source      VARCHAR(255) NULL,
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
    ADD CONSTRAINT fk_usurol_on_role FOREIGN KEY (rode_id) REFERENCES `role` (id);

ALTER TABLE usuarios_role
    ADD CONSTRAINT fk_usurol_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);