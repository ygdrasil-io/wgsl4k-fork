#version 450 core
precision highp float;
precision highp int;


int use_me() {
    return 10;
}

int use_return() {
    return use_me();
}

int use_assign_var() {
    int q = use_me();
    return q;
}

int use_assign_let() {
    int q = use_me();
    return q;
}

void use_phony_assign() {
    use_me();
}

void wgsl_main() {
    use_return();
    use_assign_var();
    use_assign_let();
    use_phony_assign();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}
