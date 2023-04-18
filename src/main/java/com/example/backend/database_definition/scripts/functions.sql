create or replace function find_semester_type_id_by_name(_name text) returns bigint as
$$
declare
    _id bigint;

begin
    select id
    into _id
    from public.semester_type
    where name = _name;

    return _id;
end;
$$ language plpgsql;

create or replace function find_year_id_by_name(_name text) returns bigint as
$$

declare
    _id bigint;

begin
    select id
    into _id
    from public.year
    where name = _name;

    return _id;
end;

$$ language plpgsql;

create or replace function find_role_id_by_name(_name text) returns bigint as
$$

declare
    _id bigint;

begin
    select id
    into _id
    from auth_user.roles
    where name = _name;

    return _id;
end;

$$ language plpgsql;

create or replace function find_user_id_by_email(_email text) returns bigint as
$$

declare
    _id bigint;

begin
    select id
    into _id
    from auth_user.users
    where email = _email;

    return _id;
end;

$$ language plpgsql;


