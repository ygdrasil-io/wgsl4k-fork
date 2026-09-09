#version 450 core
precision highp float;
precision highp int;

struct Struct_5 {
    uint atomic_scalar;
    int[2] atomic_arr;
};
shared uint global_0;
shared int global_1;
shared Struct_5 global_2;

void wgsl_test_atomic_workgroup_uniform_load(uvec3 workgroup_id, uvec3 local_id) {
    uint active_tile_index = (workgroup_id[0] + (workgroup_id[1] * 32768));
    atomicOr(global_0, uint((active_tile_index >= 64)));
    atomicAdd(global_1, 1);
    atomicStore(global_2.atomic_scalar, 1u);
    atomicAdd(global_2.atomic_arr[0], 1);
    workgroupBarrier();
    uint scalar_val = workgroupUniformLoad(global_0);
    int signed_val = workgroupUniformLoad(global_1);
    uint struct_scalar = workgroupUniformLoad(global_2.atomic_scalar);
    int struct_arr_val = workgroupUniformLoad(global_2.atomic_arr[0]);
    if (((((scalar_val == 0u) && (signed_val > 0)) && (struct_scalar > 0u)) && (struct_arr_val > 0))) {
        return;
    }
}

uint atomicOr(uint arg_0, uint arg_1) {
}

int atomicAdd(int arg_0, int arg_1) {
}

void atomicStore(uint arg_0, uint arg_1) {
}

int atomicAdd(int arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint workgroupUniformLoad(uint arg_0) {
}

int workgroupUniformLoad(int arg_0) {
}

uint workgroupUniformLoad(uint arg_0) {
}

int workgroupUniformLoad(int arg_0) {
}

layout(local_size_x = 64, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_test_atomic_workgroup_uniform_load(workgroup_id, local_id);
}
