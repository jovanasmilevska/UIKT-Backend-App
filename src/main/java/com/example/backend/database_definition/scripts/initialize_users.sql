create
or replace function create_user_if_not_exists(_email text, _password text, _role text, _name text, _surname text) returns void as
$$

begin
    if
not exists(select id from auth_user.users where email = _email) then
        insert into auth_user.users(email, password, date_created, name, surname, username)
        values (_email, _password, now(), _name, _surname, _name);
insert into auth_user.user_roles(role_id, user_id)
values (find_role_id_by_name(_role), find_user_id_by_email(_email));
end if;
end;

$$
language plpgsql;

select create_user_if_not_exists('admin@gmail.com', '$2a$10$HUQq8rT61bAX3jq5QGhO3uQ0ueeqEo3Ag4XOTwlgpruf5qoFjvIq6',
                                 'ROLE_ADMIN', 'admin', 'adminov');
-- password for admin user: admin

