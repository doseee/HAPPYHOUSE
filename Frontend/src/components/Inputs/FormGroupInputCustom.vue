<template>
  <div :class="[{ 'input-group': hasIcon }]">
    <div v-if="addonLeftIcon" class="input-group-prepend">
      <i class="input-group-text"></i>
    </div>
    <input v-bind="$attrs" class="form-control" />
  </div>
</template>
<script>
export default {
  name: "fg-input-custom",
  props: {
    required: Boolean,
    label: String,
    error: String,
    labelClasses: String,
    inputClasses: String,
    value: {
      type: [String, Number],
      default: "",
    },
    addonRightIcon: String,
    addonLeftIcon: String,
  },
  data() {
    return {
      focused: false,
    };
  },
  computed: {
    listeners() {
      return {
        ...this.$listeners,
        input: this.updateValue,
        focus: this.onFocus,
        blur: this.onBlur,
      };
    },
    hasIcon() {
      return this.addonLeftIcon !== undefined;
    },
  },
};
</script>
<style></style>
