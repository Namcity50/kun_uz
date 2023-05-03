CREATE TABLE IF NOT EXISTS book
(
    id          serial,
    description varchar(255) DEFAULT NULL,
    title       varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- insert into profile(id)
-- values ('asd'),('asda') ON CONFLICT (id) do nothing ;


-- comment like count------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION comment_like_count_trigger_function()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
$$
BEGIN
    UPDATE comment
    SET like_count = (SELECT count(comment_like.id) AS count
                      FROM comment_like
                      WHERE comment_id = NEW.comment_id and status = 'LIKE'),
        dislike_count = (SELECT count(comment_like.id) AS count
                         FROM comment_like
                         WHERE comment_id = NEW.comment_id and status = 'DISLIKE')
    WHERE id = NEW.comment_id;
    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER comment_like_count_trigger
    AFTER INSERT
    ON comment_like
    FOR EACH ROW
EXECUTE FUNCTION comment_like_count_trigger_function();

-- article like count--------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION article_like_count_trigger_function()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
$$
BEGIN
    UPDATE article
    SET like_count = (SELECT count(*) AS count
                      FROM article_like
                      WHERE article_id = NEW.article_id and status = 'LIKE'),
        dislike_count = (SELECT count(*) AS count
                         FROM article_like
                         WHERE article_id = NEW.article_id and status = 'DISLIKE')
    WHERE id = NEW.article_id;
    RETURN NEW;
END;
$$;

CREATE OR REPLACE TRIGGER article_like_count_trigger
    AFTER INSERT OR UPDATE OR DELETE
    ON article_like
    FOR EACH ROW
EXECUTE FUNCTION article_like_count_trigger_function();

